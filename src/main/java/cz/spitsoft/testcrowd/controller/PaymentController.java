package cz.spitsoft.testcrowd.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.PayPalService;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.utils.PayPalPaymentIntent;
import cz.spitsoft.testcrowd.utils.PayPalPaymentMethod;
import cz.spitsoft.testcrowd.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {
    public static final String PAYPAL_SUCCESS_URL = "/pay/success";
    public static final String PAYPAL_CANCEL_URL = "/pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private PayPalService paypalService;

    @PostMapping("/pay")

    public String pay(HttpServletRequest request, @RequestParam Double sum) {
        String cancelUrl = URLUtils.getBaseURl(request) + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl(request) + PAYPAL_SUCCESS_URL;

        sum = sum / 100; // 100 credits = 1 USD

        try {
            Payment payment = paypalService.createPayment(
                    sum,
                    "USD",
                    PayPalPaymentMethod.PAYPAL,
                    PayPalPaymentIntent.SALE,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/users/current";
    }

    @GetMapping(PAYPAL_CANCEL_URL)
    public String cancel() {
        return "cancel";
    }

    @GetMapping(PAYPAL_SUCCESS_URL)
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("token") String token, @RequestParam("PayerID") String payerId) {

        // TODO: add a condition to avoid recharging the last amount

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {

                for (Transaction transaction : payment.getTransactions()) {
                    double total = Double.valueOf(transaction.getAmount().getTotal());
                    int credits = (int) (total * 100); // 100 credits = 1 USD

                    UserImp user = securityService.getCurrentUser();
                    user.setAccountBalance(user.getAccountBalance() + credits);
                    userService.save(user);
                }

                return "user/user-recharge-credit-success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }

        return "redirect:/";
    }

}

jQuery(document).ready(function ($) {
    $("tr.link").click(function () {
        window.location = $(this).data('href');
    });
    $(".btn-danger").click(function (e) {
        e.preventDefault();
        let r = confirm('Are you sure?');
        if (r === true) {
            window.location = $(this).attr('href');
        }
    });
});
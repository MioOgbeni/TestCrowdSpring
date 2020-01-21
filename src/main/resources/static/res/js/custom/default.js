jQuery(document).ready(function ($) {
    $("tr.link").click(function () {
        window.location = $(this).data('href');
    });
});
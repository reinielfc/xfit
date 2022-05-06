$(window).on("load", function () {
    $(".tab-link").on('show.bs.tab', function () {
        $('.tab-link').removeClass('active');
    });

    // show first tab of tab-links
    $('.tab-link').first().tab('show');
});
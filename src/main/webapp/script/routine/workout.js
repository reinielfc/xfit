$(window).on("load", function () {
    $(".tab-link").on('show.bs.tab', function() {
        $('.tab-link').removeClass('active');
    });
});
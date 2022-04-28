$(window).on("load", function () {
    // prevent dismiss dropdown on click inside
    $(".dropdown-menu").on("click.bs.dropdown", function (event) {
        event.stopPropagation();
    });

    // show first tab of tab-links
    $('.tab-link').first().tab('show')

});

// jquery extensions
$.fn.removeClassStartingWith = function (filter) {
    $(this).removeClass(function (index, className) {
        return (className.match(new RegExp("\\S*" + filter + "\\S*", 'g')) || []).join(' ')
    });
    return this;
};
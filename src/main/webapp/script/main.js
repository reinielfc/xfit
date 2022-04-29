$(window).on("load", function () {
    // prevent dismiss dropdown on click inside
    $(".dropdown-menu").on("click.bs.dropdown", function (event) {
        event.stopPropagation();
    });
});

// jquery extensions
$.fn.removeClassStartingWith = function (filter) {
    $(this).removeClass(function (index, className) {
        return (className.match(new RegExp("\\S*" + filter + "\\S*", 'g')) || []).join(' ')
    });
    return this;
};
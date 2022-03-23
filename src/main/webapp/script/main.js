$(window).on("load", function () {
    // scrapped enetered info on lost focus
    $(".modal").on("hidden.bs.modal", function () {
        $(this).find("form").trigger("reset");
    });

    // prevent dismiss dropdown on click inside
    $(".dropdown-menu").on("click.bs.dropdown", function (event) {
        event.stopPropagation();
    })
});

// jquery extension
$.fn.removeClassStartingWith = function (filter) {
    $(this).removeClass(function (index, className) {
        return (className.match(new RegExp("\\S*" + filter + "\\S*", 'g')) || []).join(' ')
    });
    return this;
};

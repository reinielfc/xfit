$(window).on("load", function () {
    // scrapped enetered info on lost focus
    $(".modal").on("hidden.bs.modal", function () {
        $(this).find("form").trigger("reset");
    });

    // prevent dismiss dropdown on click inside
    $(".dropdown-menu").on("click.bs.dropdown", function (event) {
        event.stopPropagation();
    });

    // number only input
    $(".numeric-only").on("input", function (event) {
        this.value = this.value.replace(/[^0-9]+/g, '');
    });

    // show first tab of nav-tabs
    $(".nav-tabs li:first-child button").tab("show");
    

    // configure sortables
    $(".sortable").sortable({
      handle: ".sortable-item-handle",
      cursor: "grabbing"
    });

});

// jquery extensions
$.fn.removeClassStartingWith = function (filter) {
    $(this).removeClass(function (index, className) {
        return (className.match(new RegExp("\\S*" + filter + "\\S*", 'g')) || []).join(' ')
    });
    return this;
};

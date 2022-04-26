$(window).on("load", function () {
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
$(window).on("load", function () {
    // configure sortables
    $(".sortable").sortable({
        handle: ".sortable-item-handle",
        cursor: "grabbing"
    });
});
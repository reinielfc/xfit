$(window).on("load", function () {
    $('.exercise-is-favorite-btn').on('change', function () {
        let exercise = $(this).data('xf-target');
        
        $(exercise)
            .find('.exercise-is-favorite')
            .toggleClass('d-none');
    });

    $('.custom-exercise-edit-btn').on('click', function () {
        let $this = $(this)
        let $exercise = $($this.data('xf-target'));

        let title = $exercise.find('.exercise-title').text();
        let description = $exercise.find('.exercise-primer').text();

        let modal = $($this.data('bs-target'));
        modal.find('.custom-exercise-title').val(title);
        modal.find('.custom-exercise-description').val(description);
    });

    $('form').on('submit', function () {
        $()
    });
});

$(window).on('unload', function () {
    $('#exercise-list-form').trigger('submit');
});

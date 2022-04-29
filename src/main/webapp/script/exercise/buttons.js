$(window).on("load", function () {
    $('.exercise-is-favorite-check').on('change', function () {
        let $this = $(this);
        let $hidden = $this.prev('.input-hidden');

        let value = $this.is(":checked");
        let originalValue = $hidden.data('xf-value');

        if (value !== originalValue) {
            let name = $hidden.data('xf-name');
            $hidden.attr('name', name);
            $hidden.val(value);
        } else {
            $hidden.removeAttr('name value');
        }

        let $exercise = $($this.data('xf-parent'));

        $exercise.find('.exercise-is-favorite')
                .toggleClass('d-none show hide');

    });

    $('.custom-exercise-edit-btn').on('click', function () {
        let $this = $(this)
        let $exercise = $($this.data('xf-parent'));

        let name = $exercise.data('xf-name');
        let title = $exercise.find('.exercise-title').text();
        let description = $exercise.find('.exercise-primer').text();

        let $modal = $($this.data('bs-target'));
        $modal.find('.custom-exercise-name').val(name);
        $modal.find('.custom-exercise-title').val(title);
        $modal.find('.custom-exercise-description').val(description);
    });

});

$(window).on('unload', function () {
    $('#exercise-list-form').trigger('submit');
});

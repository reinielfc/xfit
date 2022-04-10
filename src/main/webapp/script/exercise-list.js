$(window).on('load', function () {

    let searchQuery = '';

    var exerciseFilters = {
        '.exercise-type': [],
        '.exercise-primary': [],
        '.exercise-muscle': [],
        '.exercise-equipment': [],
    };

    let filterFunction = function () {
        const $this = $(this);

        // TODO: delete this after video, fixes db problem of quotes
        $(this).html($(this).html().replace(/[â€™œ]+/g, "\'"))

        return (!searchQuery.trim() || $this.text().match(searchQuery.toLowerCase()))
            && Object.keys(exerciseFilters).every(field => {
                let values = exerciseFilters[field]

                return !values.length || $this
                    .find(field)
                    .map(function () { return $(this).data('field-value') })
                    .get()
                    .some($fieldValue => values
                        .some(value => !value.trim() || value === $fieldValue)
                    );
            });
    }

    var options = {
        itemSelector: '.exercise',
        getSortData: {
            title: '.exercise-title',
            type: '.exercise-type',
            primary: '.exercise-primary',
            secondary: '.exercise-secondary',
            equipment: '.exercise-equipment'
        },
        filter: filterFunction
    }

    let $exerciseList = $('#exercise-list').isotope(options);

    $('.exercise-list-filter select').on('change', function () {
        let $this = $(this);
        let field = $this.data('filter-target');
        let value = $this.val();
        exerciseFilters[field] = [value];
        $exerciseList.isotope(options);
    });

    $('#exercise-list-sort select').on('change', function () {
        $exerciseList.isotope({ sortBy: $(this).val() });
    });

    function debounce(fn, threshold) {
        var timeout;
        threshold = threshold || 100;
        return function debounced() {
            clearTimeout(timeout);
            var args = arguments;
            var _this = this;

            function delayed() {
                fn.apply(_this, args);
            }
            timeout = setTimeout(delayed, threshold);
        };
    }

    $('#exercise-list-search').on('input', debounce(function () {
        searchQuery = $(this).val();
        $exerciseList.isotope(options);
    }, 200));
});
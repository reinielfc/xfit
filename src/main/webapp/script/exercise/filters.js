$(window).on('load', function () {

    let searchQuery = '';

    var exerciseFilters = {
        '.exercise-type': [],
        '.exercise-muscle': [],
        '.exercise-equipment': [],
        '.exercise-is-custom': [],
        '.exercise-is-favorite': [],
        '.user-owns-equipment': [],
    };

    var filterFn = function () {
        let $this = $(this);

        return (!searchQuery.trim() || $this.text().match(new RegExp(`${searchQuery}`, 'i')))
            && Object.keys(exerciseFilters).every(field => {
                let values = exerciseFilters[field]

                return !values.length || $this
                    .find(field)
                    .map(function () { return $(this).data('xf-value') })
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
            primary: '.exercise-muscle-primary',
            secondary: '.exercise-muscle-secondary',
            equipment: '.exercise-equipment'
        },
        filter: filterFn
    }

    let $exerciseList = $('#exercise-list').isotope(options);
    
    $('.exercise-list-user-filter').on('change', function () {
        let $this = $(this);
        let value = $this.is(':checked') ? 'true' : '';
        
        $this.val(value);
    });

    let listFilterFn = function () {
        let $this = $(this);
        let field = $this.data('xf-filter-target');
        let value = $this.val();
        
        exerciseFilters[field] = [value];
        $exerciseList.isotope(options);
    }

    $('.exercise-list-filter select').on('change', listFilterFn);

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
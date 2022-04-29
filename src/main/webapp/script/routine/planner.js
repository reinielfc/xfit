/// <reference path="/home/rei/prj/xfit/frontEnd/node_modules/jquery-sortablejs/jquery-sortable.js" />

$(window).on("load", function () {
    // configure sortables
    $(".sortable").sortable({
        cursor: "grabbing",
        handle: ".sortable-item-handle",
        stop: function (event, ui) {
            $(".plan").each(function () {
                let $this = $(this);
                let index = $this.index();

                $this.find('.plan-position').val(index);
            })
        }
    });

    $('.plan-delete-btn').on('click', function () {
        let $plan = $($(this).data('xf-parent'));

        $plan.remove()
    });


    $('.exercise-add-btn').on('click', function () {
        let $exercise = $($(this).data('xf-parent'));

        let exercise = {
            name: $exercise.data('xf-name'),
            title: $exercise.find('.exercise-title').prop('outerHTML'),
            badges: $exercise.find('.exercise-badges').prop('outerHTML'),
            primer: $exercise.find('.exercise-primer').prop('outerHTML'),
        }

        let $activeDayPane = $('.day.tab-pane.active.show');
        let day = $activeDayPane.data('xf-day');
        let position = $activeDayPane.find('.plan').length;

        let plan = getPlan(exercise, day, position);
        let $list = $activeDayPane.find('.plan-list');

        $list.append(plan);
    });

    let getPlan = function (exercise, day, position) {
        let planId = `plan-${day}-${position}`
        let planMapping = `plan[${day}][${position}]`

        let planString = 
        `<li id="${planId}" class="plan list-group-item d-flex w-100 pe-0">
        <div class="ms-2 flex-fill">
      
          <div class="d-flex w-100">
            <div class="flex-fill">
              <div class="d-block">
                ${exercise.title}

                <!-- badges -->
                ${exercise.badges}
      
                ${exercise.primer}
              </div>
      
              <div class="d-flex small text-muted me-auto">
                <div class="m-1">
                  <i class="fa fa-bars"></i>
                  &times;
                  <span class="sets-count">0</span>
                </div>
      
                <div class="m-1">
                  <i class="fa fa-arrow-rotate-right"></i>
                  &times;
                  <span class="reps-count">0</span>
                </div>
      
                <div class="m-1">
                  <i class="fa fa-weight-hanging"></i>
                  <span class="weight-amount">0</span> lbs
                </div>
              </div>
            </div>
      
            <div class="d-flex align-items-center p-1">
              <button class="btn btn-outline-dark m-1 text-nowrap" type="button" data-xf-collapse-group=".plan-form" data-bs-toggle="collapse" data-bs-target="#${planId}-form">
                  <i class="fa fa-pen-to-square"></i>
                  Edit
              </button>
            </div>
      
            <div class="sortable-item-handle d-flex align-items-center p-3">
              <i class="fa fa-grip-lines-vertical"></i>
            </div>
          </div>
      
          <div id="${planId}-form" class="plan-form collapse show">
            <div class="p-1 me-2">
              <div class="d-flex flex-row">
                <div class="ms-0">
                  <button class="plan-delete-btn btn btn-outline-danger m-1" type="button" data-xf-parent="#${planId}" >
                    <i class="fa fa-trash-can"></i>
                  </button>
                </div>
      
                <input type="hidden" class="plan-exercise-name" name="${planMapping}[exerciseName]" value="${exercise.name}" />
                <input type="hidden" class="plan-position" name="${planMapping}[position]" value="${position}" />
      
                <div class="input-group mx-1">
                  <span class="input-group-text px-2"><i class="fa fa-bars"></i></span>
                  <input class="form-control" type="number" name="${planMapping}[sets]" value="0" data-xf-bind="#${planId} .sets-count" placeholder="5" min="0" max="512" />
                  <span class="input-group-text px-2">sets</span>
                </div>
      
                <div class="input-group mx-1">
                  <span class="input-group-text px-2"><i class="fa fa-arrow-rotate-right"></i></span>
                  <input class="form-control" type="number" name="${planMapping}[reps]" value="0" data-xf-bind="#${planId} .reps-count" placeholder="10" min="0" max="512" />
                  <span class="input-group-text px-2">reps</span>
                </div>
      
                <div class="input-group mx-1">
                  <span class="input-group-text px-2"><i class="fa fa-weight-hanging"></i></span>
                  <input class="form-control" type="number" name="${planMapping}[weight]" value="0" data-xf-bind="#${planId} .weight-amount" placeholder="25" min="0" max="4096" />
                  <span class="input-group-text px-2">lbs</span>
                </div>
              </div>
            </div>
          </div>
              
        </div>
      </li>`

        return $.parseHTML(planString)[0]
    }
});
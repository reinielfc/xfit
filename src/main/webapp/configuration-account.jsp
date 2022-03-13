<div class="modal fade" id="signup-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-fullscreen-md-down" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Edit</h5>
        </div>
        <form action="ConfigurationController" method="POST">
            < class="modal-body">
              <!--name-->
              <div class="form-group">
                <label for="first_name">firstname</label>
                <div class="input-group">
                  <input
                    type="text"
                    id="first_name"
                    name="first_name"
                    class="form-control"
                    placeholder="jsmith"
                  />
                  <span class="input-group-text"><i class="fa fa-user"></i></span>
                </div>
              </div>
aca es donde va todo lo del html
<!--lastname-->
<div class="form-group">
    <label for="last_name">lastname</label>
    <div class="input-group">
      <input
        type="text"
        id="last_name"
        name="last_name"
        class="form-control"
        placeholder="jsmith"
      />
      <span class="input-group-text"><i class="fa fa-user"></i></span>
    </div>
  </div>
  <!--email-->
  <div class="form-group">
    <label for="email">email</label>
    <div class="input-group">
      <input
        type="email"
        id="email"
        name="email"
        class="form-control"
        placeholder="jsmith@example.com"
      />
      <span class="*Adjust*"><i class="fa fa-user"></i></span>
    </div>
  </div>
  <!--password-->
  <div class="form-group">
    <label for="password">Password</label>
    <div class="input-group">
      <input
        type="password"
        name="password"
        id="signup-password"
        class="form-control"
        placeholder="********"
      />
      <span class="input-group-text"><i class="fa fa-lock"></i></span>
    </div>
  </div>
<!--weight-->
<div class="form-group">
    <label for="weight">weight</label>
    <div class="input-group">
      <input
        type="number"
        min="0"
        id="weight"
        name="weight"
        class="form-control"
        placeholder="100pounds"
      />
      <span class="input-group-text"><i class="fa fa-user"></i></span>
    </div>
  </div>
  <!--height-->
<div class="form-group">
    <label for="height">height</label>
    <div class="input-group">
      <input
        type="number"
        min="0"
        id="height"
        name="height"
        class="form-control"
        placeholder="5'10"
      />
      <span class="input-group-text"><i class="fa fa-user"></i></span>
    </div>
  </div>
  <!--experience-->
    <div class="form-group">
      <label for="experience">experience</label>
      <div class="input-group">
        <input
        type="radio"
        id="experience"
        name="experience"
        class="form-control"
        placeholder="select"
        />
        <span class="input-group-text"><i class="fa fa-user"></i></span>
      </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" type="submit">Save</button>
      </div>






        </form--- cerrarrlo
      </div>
    </div>
</div>

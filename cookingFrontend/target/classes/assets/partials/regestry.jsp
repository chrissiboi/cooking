<div class="col-md-6 col-md-offset-3">
    <div class="page-header">
        <h1>
            Please fill out the form.
        </h1>
    </div>
    <form  name="signUpForm" novalidate method="post" ng-submit="sending()">
        <div id="messages" >{{error}}</div>
        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" class="form-control" ng-class="userError(signUpForm.username.$valid)" ng-model="formData.username" ng-minLength="3" required>
            <span class='help-block' ng-show="signUpForm.username.$invalid && signUpForm.username.$dirty">Username must contain 3 letters!</span>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" class="form-control" ng-model="formData.email" required>
            <span class="help-block"></span>
        </div>
        <div class="form-group">
            <div class="col-md-6">
                <label>Password:</label>
                <input type="password" name="password" class="form-control" ng-model="formData.password" required>
                <span class="help-block"></span>
            </div>
            <div class="col-md-6">
                <label>Repeat Password</label>
                <input type="password" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label>Firstname:</label>
            <input type="text" name="firstname" class="form-control" ng-model="formData.firstname">
            <span class="help-block"></span>
        </div>
        <div class="form-group">
            <label>Lastname:</label>
            <input type="text" name="lastname" class="form-control" ng-model="formData.lastname">
            <span class="help-block"></span>
        </div>
        <button type="submit" class="btn btn-success btn-lg btn-block" ng-show="signUpForm.$dirty" data-toggle="modal" data-target="#myModal">
            Submit!
        </button>

        <pre>{{formData}}</pre>
    </form>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-click="redirectToHome()">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel"><div ng-bind-html="registrationResult"></div></h4>
                </div>
                <div class="modal-body">
                    <h5>You can login now.</h5>
                </div>
                <!--
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
                -->
            </div>
        </div>
    </div>
</div>
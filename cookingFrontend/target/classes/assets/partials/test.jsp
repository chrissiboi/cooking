<div ng-controller="Ctrl">
    <input type="file" file-upload multiple/>
    <ul>
        <li ng-repeat="file in files">{{file.name}}</li>
    </ul>
    <button ng-click="save()">send</button>
</div>
define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/pymeModel', 'controller/pymeController'], function() {
    App.Component.PymeComponent = App.Component._CRUDComponent.extend({
        name: 'pyme',
        model: App.Model.PymeModel,
        listModel: App.Model.PymeList,
        controller : App.Controller.PymeController
    });
    return App.Component.PymeComponent;
});
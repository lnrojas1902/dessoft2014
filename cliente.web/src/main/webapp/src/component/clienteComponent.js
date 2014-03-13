define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/clienteModel', 'controller/clienteController'], function() {
    App.Component.ClienteComponent = App.Component._CRUDComponent.extend({
        name: 'cliente',
        model: App.Model.ClienteModel,
        listModel: App.Model.ClienteList,
        controller : App.Controller.ClienteController
    });
    return App.Component.ClienteComponent;
});
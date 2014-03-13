define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/carritoDeComprasModel', 'controller/carritoDeComprasController'], function() {
    App.Component.CarritoDeComprasComponent = App.Component._CRUDComponent.extend({
        name: 'carritoDeCompras',
        model: App.Model.CarritoDeComprasModel,
        listModel: App.Model.CarritoDeComprasList,
        controller : App.Controller.CarritoDeComprasController
    });
    return App.Component.CarritoDeComprasComponent;
});
define(['model/_carritoDeComprasModel'], function() {
    App.Model.CarritoDeComprasModel = App.Model._CarritoDeComprasModel.extend({

    });

    App.Model.CarritoDeComprasList = App.Model._CarritoDeComprasList.extend({
        model: App.Model.CarritoDeComprasModel
    });

    return  App.Model.CarritoDeComprasModel;

});
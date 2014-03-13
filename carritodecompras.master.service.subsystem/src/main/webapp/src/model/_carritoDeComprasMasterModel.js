define([], function() {
    App.Model._CarritoDeComprasMasterModel = Backbone.Model.extend({
     
    });

    App.Model._CarritoDeComprasMasterList = Backbone.Collection.extend({
        model: App.Model._CarritoDeComprasMasterModel,
        initialize: function() {
        }

    });
    return App.Model._CarritoDeComprasMasterModel;
    
});
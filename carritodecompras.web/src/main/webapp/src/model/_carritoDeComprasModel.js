define([], function() {
    App.Model._CarritoDeComprasModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._CarritoDeComprasList = Backbone.Collection.extend({
        model: App.Model._CarritoDeComprasModel,
        initialize: function() {
        }

    });
    return App.Model._CarritoDeComprasModel;
});
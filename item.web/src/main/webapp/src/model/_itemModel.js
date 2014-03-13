define([], function() {
    App.Model._ItemModel = Backbone.Model.extend({
        defaults: {
 
		 'idProducto' : ''
 ,  
		 'name' : ''
 ,  
		 'cantidad' : ''
 ,  
		 'estado' : ''
 ,  
		 'descuento' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ItemList = Backbone.Collection.extend({
        model: App.Model._ItemModel,
        initialize: function() {
        }

    });
    return App.Model._ItemModel;
});
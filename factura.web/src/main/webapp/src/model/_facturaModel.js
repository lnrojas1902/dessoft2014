define([], function() {
    App.Model._FacturaModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'valor' : ''
 ,  
		 'tipo' : ''
 ,  
		 'tipoDePago' : ''
 ,  
		 'fechaDeRealizacion' : ''
 ,  
		 'fechaEsperadaDeEntrega' : ''
 ,  
		 'direccionEntrega' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fechaDeRealizacion'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaDeRealizacion'), this);
             }
             if(name=='fechaEsperadaDeEntrega'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaEsperadaDeEntrega'), this);
             }
         return this.get(name);
        }
    });

    App.Model._FacturaList = Backbone.Collection.extend({
        model: App.Model._FacturaModel,
        initialize: function() {
        }

    });
    return App.Model._FacturaModel;
});
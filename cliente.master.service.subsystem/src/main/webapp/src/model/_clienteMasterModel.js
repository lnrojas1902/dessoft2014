define([], function() {
    App.Model._ClienteMasterModel = Backbone.Model.extend({
     
    });

    App.Model._ClienteMasterList = Backbone.Collection.extend({
        model: App.Model._ClienteMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ClienteMasterModel;
    
});
define(['model/_clienteModel'], function() {
    App.Model.ClienteModel = App.Model._ClienteModel.extend({

    });

    App.Model.ClienteList = App.Model._ClienteList.extend({
        model: App.Model.ClienteModel
    });

    return  App.Model.ClienteModel;

});
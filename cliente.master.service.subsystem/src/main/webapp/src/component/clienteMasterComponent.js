define(['controller/selectionController', 'model/cacheModel', 'model/clienteMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/clienteComponent',
 'component/facturaComponent'
 
 ],function(SelectionController, CacheModel, ClienteMasterModel, CRUDComponent, TabController, ClienteComponent,
 FacturaComponent
 ) {
    App.Component.ClienteMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('clienteMaster');
            var uComponent = new ClienteComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-cliente-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-cliente-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-cliente-list', function() {
                self.hideChilds();
            });
            Backbone.on('cliente-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'cliente-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-cliente-save', function(params) {
                self.model.set('clienteEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var facturaModels = self.facturaComponent.componentController.facturaModelList;
                self.model.set('listFactura', []);
                self.model.set('createFactura', []);
                self.model.set('updateFactura', []);
                self.model.set('deleteFactura', []);
                for (var i = 0; i < facturaModels.models.length; i++) {
                    var m = facturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < facturaModels.deletedModels.length; i++) {
                    var m = facturaModels.deletedModels[i];
                    self.model.get('deleteFactura').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Factura", name: "factura", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ClienteMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.facturaComponent = new FacturaComponent();
                    self.facturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.FacturaModel), self.model.get('listFactura'));
                    self.facturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.FacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.FacturaModel, App.Model.FacturaList, self.facturaModels)
                    });
                    self.facturaComponent.render(self.tabs.getTabHtmlId('factura'));
                    Backbone.on(self.facturaComponent.componentId + '-post-factura-create', function(params) {
                        params.view.currentFacturaModel.setCacheList(params.view.facturaModelList);
                    });
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ClienteMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ClienteMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.ClienteMasterComponent;
});
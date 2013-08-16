/*! kt3m-ui 2013-07-25 */
(function(exports, global) {
    global["ktUi"] = exports;
    var ktUi = angular.module("kt.ui", [ "kt.ui.itemSelector", "kt.ui.table", "kt.ui.table.pagination", "kt.ui.grid", "kt.ui.tab", "kt.ui.progressbar", "kt.ui.service", "kt.ui.tree", "kt.ui.chart", "kt.ui.map", "kt.ui.navigation", "kt.ui.dialog", "kt.ui.buttons", "kt.ui.modal", "kt.ui.filter" ]);
    var ktFilter = angular.module("kt.ui.filter", [ "kt.ui.filter.startFrom" ]);
    var ktFilter = angular.module("kt.ui.service", [ "kt.ui.service.transition" ]);
    angular.module("kt.ui.filter.startFrom", []).filter("startFrom", function() {
        return function(input, start) {
            start = +start;
            return input.slice(start);
        };
    });
    angular.module("kt.ui.service.transition", []).factory("$transition", [ "$q", "$timeout", "$rootScope", function($q, $timeout, $rootScope) {
        var $transition = function(element, trigger, options) {
            options = options || {};
            var deferred = $q.defer();
            var endEventName = $transition[options.animation ? "animationEndEventName" : "transitionEndEventName"];
            var transitionEndHandler = function(event) {
                $rootScope.$apply(function() {
                    element.unbind(endEventName, transitionEndHandler);
                    deferred.resolve(element);
                });
            };
            if (endEventName) {
                element.bind(endEventName, transitionEndHandler);
            }
            $timeout(function() {
                if (angular.isString(trigger)) {
                    element.addClass(trigger);
                } else if (angular.isFunction(trigger)) {
                    trigger(element);
                } else if (angular.isObject(trigger)) {
                    element.css(trigger);
                }
                if (!endEventName) {
                    deferred.resolve(element);
                }
            });
            deferred.promise.cancel = function() {
                if (endEventName) {
                    element.unbind(endEventName, transitionEndHandler);
                }
                deferred.reject("Transition cancelled");
            };
            return deferred.promise;
        };
        var transElement = document.createElement("trans");
        var transitionEndEventNames = {
            WebkitTransition: "webkitTransitionEnd",
            MozTransition: "transitionend",
            OTransition: "oTransitionEnd",
            transition: "transitionend"
        };
        var animationEndEventNames = {
            WebkitTransition: "webkitAnimationEnd",
            MozTransition: "animationend",
            OTransition: "oAnimationEnd",
            transition: "animationend"
        };
        function findEndEventName(endEventNames) {
            for (var name in endEventNames) {
                if (transElement.style[name] !== undefined) {
                    return endEventNames[name];
                }
            }
        }
        $transition.transitionEndEventName = findEndEventName(transitionEndEventNames);
        $transition.animationEndEventName = findEndEventName(animationEndEventNames);
        return $transition;
    } ]);
    angular.module("kt.ui.buttons", []).constant("buttonConfig", {
        activeClass: "active",
        toggleEvent: "click"
    }).directive("ktBtnRadio", [ "buttonConfig", function(buttonConfig) {
        var activeClass = buttonConfig.activeClass || "active";
        var toggleEvent = buttonConfig.toggleEvent || "click";
        return {
            require: "ngModel",
            link: function(scope, element, attrs, ngModelCtrl) {
                ngModelCtrl.$render = function() {
                    element.toggleClass(activeClass, angular.equals(ngModelCtrl.$modelValue, scope.$eval(attrs.ktBtnRadio)));
                };
                element.bind(toggleEvent, function() {
                    if (!element.hasClass(activeClass)) {
                        scope.$apply(function() {
                            ngModelCtrl.$setViewValue(scope.$eval(attrs.ktBtnRadio));
                            ngModelCtrl.$render();
                        });
                    }
                });
            }
        };
    } ]).directive("ktBtnCheckbox", [ "buttonConfig", function(buttonConfig) {
        var activeClass = buttonConfig.activeClass || "active";
        var toggleEvent = buttonConfig.toggleEvent || "click";
        return {
            require: "ngModel",
            link: function(scope, element, attrs, ngModelCtrl) {
                var trueValue = scope.$eval(attrs.ktBtnCheckboxTrue);
                var falseValue = scope.$eval(attrs.ktBtnCheckboxFalse);
                trueValue = angular.isDefined(trueValue) ? trueValue : true;
                falseValue = angular.isDefined(falseValue) ? falseValue : false;
                ngModelCtrl.$render = function() {
                    element.toggleClass(activeClass, angular.equals(ngModelCtrl.$modelValue, trueValue));
                };
                element.bind(toggleEvent, function() {
                    scope.$apply(function() {
                        ngModelCtrl.$setViewValue(element.hasClass(activeClass) ? falseValue : trueValue);
                        ngModelCtrl.$render();
                    });
                });
            }
        };
    } ]);
    angular.module("kt.ui.chart", []).directive("ktChart", [ function() {
        return {
            scope: {
                data: "="
            },
            controller: function($scope) {
                $scope.mainTitle = [];
                $scope.subTitle = [];
                $scope.tooltip = [];
                $scope.legend = [];
                $scope.xaxis = [];
                $scope.yaxis = [];
                $scope.datalabel = [];
            },
            restrict: "ACM",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    type: iAttrs.type,
                    margintop: iAttrs.margintop,
                    marginbottom: iAttrs.marginbottom,
                    marginleft: iAttrs.marginleft,
                    marginright: iAttrs.marginright,
                    backgroundColor: iAttrs.backgroundcolor,
                    withlabel: iAttrs.withlabel,
                    height: iAttrs.height || null,
                    width: iAttrs.width || null
                });
                initValue();
                showChart();
                function initValue() {
                    $scope.legend.enabled = "";
                    if ($scope.datalabel.withlabel == undefined) {
                        $scope.datalabel.withlabel = "";
                    }
                }
                function showChart() {
                    jQuery(iElm).highcharts({
                        chart: {
                            type: options.type,
                            backgroundColor: options.backgroundColor,
                            marginTop: options.margintop,
                            marginBottom: options.marginbottom,
                            marginRight: options.marginright,
                            marginLeft: options.marginleft,
                            height: options.height,
                            width: options.width,
                            zoomType: "xy"
                        },
                        credits: {
                            enabled: false
                        },
                        title: {
                            style: {
                                color: $scope.mainTitle.fontcolor,
                                fontWeight: $scope.mainTitle.fontweight,
                                fontSize: $scope.mainTitle.fontsize
                            },
                            align: $scope.mainTitle.align,
                            text: $scope.mainTitle.text
                        },
                        subtitle: {
                            style: {
                                color: $scope.subTitle.fontcolor,
                                fontWeight: $scope.subTitle.fontweight,
                                fontSize: $scope.subTitle.fontsize
                            },
                            align: $scope.subTitle.align,
                            text: $scope.subTitle.text
                        },
                        xAxis: {
                            title: {
                                text: $scope.xaxis.text
                            },
                            categories: $scope.xaxis.category
                        },
                        yAxis: {
                            title: {
                                text: $scope.yaxis.text
                            },
                            categories: $scope.yaxis.category
                        },
                        tooltip: {
                            valuePrefix: $scope.tooltip.valueprefix,
                            valueSuffix: $scope.tooltip.valuesuffix
                        },
                        legend: {
                            enabled: $scope.legend.enabled.toUpperCase() == "FALSE" ? false : true,
                            layout: $scope.legend.layout,
                            align: $scope.legend.align,
                            verticalAlign: $scope.legend.verticalalign,
                            x: Number($scope.legend.x),
                            y: Number($scope.legend.y)
                        },
                        plotOptions: {
                            bar: {
                                dataLabels: {
                                    enabled: $scope.datalabel.withlabel.toUpperCase() == "TRUE" ? true : false,
                                    format: "{y:." + $scope.datalabel.point + "f}"
                                }
                            },
                            column: {
                                dataLabels: {
                                    enabled: $scope.datalabel.withlabel.toUpperCase() == "TRUE" ? true : false,
                                    format: "{y:." + $scope.datalabel.point + "f}"
                                }
                            },
                            line: {
                                dataLabels: {
                                    enabled: $scope.datalabel.withlabel.toUpperCase() == "TRUE" ? true : false,
                                    format: "{y:." + $scope.datalabel.point + "f}"
                                }
                            },
                            scatter: {
                                marker: {
                                    radius: 5
                                },
                                tooltip: {
                                    headerFormat: "<b>{series.name}</b><br>"
                                },
                                dataLabels: {
                                    enabled: $scope.datalabel.withlabel.toUpperCase() == "TRUE" ? true : false,
                                    format: "{y:." + $scope.datalabel.point + "f}"
                                }
                            },
                            series: {
                                stacking: $scope.datalabel.stacking
                            },
                            pie: {
                                allowPointSelect: true,
                                showInLegend: $scope.legend.enabled.toUpperCase() == "FALSE" ? false : true,
                                cursor: "pointer",
                                dataLabels: {
                                    enabled: $scope.datalabel.withlabel.toUpperCase() == "TRUE" ? true : false,
                                    formatter: function() {
                                        return "<b>" + this.point.name + "</b>: " + this.percentage + " %";
                                    }
                                }
                            }
                        },
                        series: $scope.data
                    });
                }
                $scope.$watch("data", function(newScopeData, oldScopeData) {
                    $scope.data = newScopeData;
                    showChart();
                }, true);
            }
        };
    } ]).directive("maintitle", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    text: iAttrs.text,
                    fontcolor: iAttrs.fontcolor,
                    fontsize: iAttrs.fontsize,
                    fontweight: iAttrs.fontweight,
                    align: iAttrs.align
                });
                $scope.mainTitle = angular.extend({}, options);
            }
        };
    } ]).directive("subtitle", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    text: iAttrs.text,
                    fontcolor: iAttrs.fontcolor,
                    fontsize: iAttrs.fontsize,
                    fontweight: iAttrs.fontweight,
                    align: iAttrs.align
                });
                $scope.subTitle = angular.extend({}, options);
            }
        };
    } ]).directive("tooltip", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    valueprefix: iAttrs.valueprefix,
                    valuesuffix: iAttrs.valuesuffix
                });
                $scope.tooltip = angular.extend({}, options);
            }
        };
    } ]).directive("legend", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    enabled: iAttrs.enabled,
                    layout: iAttrs.layout,
                    align: iAttrs.align,
                    verticalalign: iAttrs.verticalalign,
                    x: iAttrs.x,
                    y: iAttrs.y
                });
                $scope.legend = angular.extend({}, options);
            }
        };
    } ]).directive("xaxis", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    text: iAttrs.text,
                    category: $scope.$parent[iAttrs.category]
                });
                $scope.xaxis = angular.extend({}, options);
            }
        };
    } ]).directive("yaxis", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    text: iAttrs.text,
                    category: $scope.$parent[iAttrs.category]
                });
                $scope.yaxis = angular.extend({}, options);
            }
        };
    } ]).directive("datalabel", [ function() {
        return {
            priority: 1,
            require: "^ktChart",
            restrict: "AE",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    withlabel: iAttrs.withlabel,
                    point: iAttrs.point,
                    stacking: iAttrs.stacking
                });
                $scope.datalabel = angular.extend({}, options);
            }
        };
    } ]);
    var dialogModule = angular.module("kt.ui.dialog", [ "kt.ui.service.transition" ]);
    dialogModule.controller("MessageBoxController", [ "$scope", "dialog", "model", function($scope, dialog, model) {
        $scope.title = model.title;
        $scope.message = model.message;
        $scope.buttons = model.buttons;
        $scope.close = function(res) {
            dialog.close(res);
        };
    } ]);
    dialogModule.provider("$dialog", function() {
        var defaults = {
            backdrop: true,
            dialogClass: "modal",
            backdropClass: "modal-backdrop",
            transitionClass: "fade",
            triggerClass: "in",
            resolve: {},
            backdropFade: false,
            dialogFade: false,
            keyboard: true,
            backdropClick: true
        };
        var globalOptions = {};
        var activeBackdrops = {
            value: 0
        };
        this.options = function(value) {
            globalOptions = value;
        };
        this.$get = [ "$http", "$document", "$compile", "$rootScope", "$controller", "$templateCache", "$q", "$transition", "$injector", function($http, $document, $compile, $rootScope, $controller, $templateCache, $q, $transition, $injector) {
            var body = $document.find("body");
            function createElement(clazz) {
                var el = angular.element("<div>");
                el.addClass(clazz);
                return el;
            }
            function Dialog(opts) {
                var self = this, options = this.options = angular.extend({}, defaults, globalOptions, opts);
                this._open = false;
                this.backdropEl = createElement(options.backdropClass);
                if (options.backdropFade) {
                    this.backdropEl.addClass(options.transitionClass);
                    this.backdropEl.removeClass(options.triggerClass);
                }
                this.modalEl = createElement(options.dialogClass);
                if (options.dialogFade) {
                    this.modalEl.addClass(options.transitionClass);
                    this.modalEl.removeClass(options.triggerClass);
                }
                this.handledEscapeKey = function(e) {
                    if (e.which === 27) {
                        self.close();
                        e.preventDefault();
                        self.$scope.$apply();
                    }
                };
                this.handleBackDropClick = function(e) {
                    self.close();
                    e.preventDefault();
                    self.$scope.$apply();
                };
                this.handleLocationChange = function() {
                    self.close();
                };
            }
            Dialog.prototype.isOpen = function() {
                return this._open;
            };
            Dialog.prototype.open = function(templateUrl, controller) {
                var self = this, options = this.options;
                if (templateUrl) {
                    options.templateUrl = templateUrl;
                }
                if (controller) {
                    options.controller = controller;
                }
                if (!(options.template || options.templateUrl)) {
                    throw new Error("Dialog.open expected template or templateUrl, neither found. Use options or open method to specify them.");
                }
                this._loadResolves().then(function(locals) {
                    var $scope = locals.$scope = self.$scope = locals.$scope ? locals.$scope : $rootScope.$new();
                    self.modalEl.html(locals.$template);
                    if (self.options.controller) {
                        var ctrl = $controller(self.options.controller, locals);
                        self.modalEl.children().data("ngControllerController", ctrl);
                    }
                    $compile(self.modalEl)($scope);
                    self._addElementsToDom();
                    setTimeout(function() {
                        if (self.options.dialogFade) {
                            self.modalEl.addClass(self.options.triggerClass);
                        }
                        if (self.options.backdropFade) {
                            self.backdropEl.addClass(self.options.triggerClass);
                        }
                    });
                    self._bindEvents();
                });
                this.deferred = $q.defer();
                return this.deferred.promise;
            };
            Dialog.prototype.close = function(result) {
                var self = this;
                var fadingElements = this._getFadingElements();
                if (fadingElements.length > 0) {
                    for (var i = fadingElements.length - 1; i >= 0; i--) {
                        $transition(fadingElements[i], removeTriggerClass).then(onCloseComplete);
                    }
                    return;
                }
                this._onCloseComplete(result);
                function removeTriggerClass(el) {
                    el.removeClass(self.options.triggerClass);
                }
                function onCloseComplete() {
                    if (self._open) {
                        self._onCloseComplete(result);
                    }
                }
            };
            Dialog.prototype._getFadingElements = function() {
                var elements = [];
                if (this.options.dialogFade) {
                    elements.push(this.modalEl);
                }
                if (this.options.backdropFade) {
                    elements.push(this.backdropEl);
                }
                return elements;
            };
            Dialog.prototype._bindEvents = function() {
                if (this.options.keyboard) {
                    body.bind("keydown", this.handledEscapeKey);
                }
                if (this.options.backdrop && this.options.backdropClick) {
                    this.backdropEl.bind("click", this.handleBackDropClick);
                }
            };
            Dialog.prototype._unbindEvents = function() {
                if (this.options.keyboard) {
                    body.unbind("keydown", this.handledEscapeKey);
                }
                if (this.options.backdrop && this.options.backdropClick) {
                    this.backdropEl.unbind("click", this.handleBackDropClick);
                }
            };
            Dialog.prototype._onCloseComplete = function(result) {
                this._removeElementsFromDom();
                this._unbindEvents();
                this.deferred.resolve(result);
            };
            Dialog.prototype._addElementsToDom = function() {
                body.append(this.modalEl);
                if (this.options.backdrop) {
                    if (activeBackdrops.value === 0) {
                        body.append(this.backdropEl);
                    }
                    activeBackdrops.value++;
                }
                this._open = true;
            };
            Dialog.prototype._removeElementsFromDom = function() {
                this.modalEl.remove();
                if (this.options.backdrop) {
                    activeBackdrops.value--;
                    if (activeBackdrops.value === 0) {
                        this.backdropEl.remove();
                    }
                }
                this._open = false;
            };
            Dialog.prototype._loadResolves = function() {
                var values = [], keys = [], templatePromise, self = this;
                if (this.options.template) {
                    templatePromise = $q.when(this.options.template);
                } else if (this.options.templateUrl) {
                    templatePromise = $http.get(this.options.templateUrl, {
                        cache: $templateCache
                    }).then(function(response) {
                        return response.data;
                    });
                }
                angular.forEach(this.options.resolve || [], function(value, key) {
                    keys.push(key);
                    values.push(angular.isString(value) ? $injector.get(value) : $injector.invoke(value));
                });
                keys.push("$template");
                values.push(templatePromise);
                return $q.all(values).then(function(values) {
                    var locals = {};
                    angular.forEach(values, function(value, index) {
                        locals[keys[index]] = value;
                    });
                    locals.dialog = self;
                    return locals;
                });
            };
            return {
                dialog: function(opts) {
                    return new Dialog(opts);
                },
                messageBox: function(title, message, buttons) {
                    return new Dialog({
                        templateUrl: "template/dialog/message.html",
                        controller: "MessageBoxController",
                        resolve: {
                            model: function() {
                                return {
                                    title: title,
                                    message: message,
                                    buttons: buttons
                                };
                            }
                        }
                    });
                }
            };
        } ];
    });
    angular.module("kt.ui.itemSelector", []).constant("uiItemSelectorConfig", {}).directive("ktItemSelector", [ "uiItemSelectorConfig", "$compile", function(uiItemSelectorConfig, $compile) {
        "use strict";
        function getTemplate(leftOption, rightOption) {
            var template = '<div class="kt-item-selector">' + '<select class="pull-left left-select" ng-model="leftModel" ng-options="' + leftOption + ' in leftItems" multiple="multiple"></select>' + '<div class="buttonGroup pull-left">' + '<button type="button" ng-show="options.showCopyButton" ng-click="moveToRigth(leftModel)" class="btn right-copy-button"><i class="icon-arrow-right"></i></button>' + '<button type="button" ng-show="options.showMoveButton" ng-click="moveToRigth(leftModel,true)" class="btn right-move-button"><i class="icon-forward"></i></button>' + '<button type="button" ng-show="options.showMoveButton" ng-click="moveToLeft(rightModel,true)" class="btn left-move-button"><i class="icon-backward"></i></button>' + '<button type="button" ng-show="options.showCopyButton" ng-click="moveToLeft(rightModel)" class="btn left-copy-button"><i class="icon-arrow-left"></i></button>' + "</div>" + '<select class="pull-left right-select" ng-model="rightModel" ng-options="' + rightOption + ' in rightItems" multiple="multiple"></select>' + '<div class="clearfix"></div>' + "</div>";
            return template;
        }
        return {
            scope: {
                leftItems: "=",
                rightItems: "=",
                leftModel: "=",
                rightModel: "="
            },
            controller: function($scope, $element, $attrs) {
                function movingAtoB(a, b) {
                    if (!!!a) return;
                    if (_.isArray(a)) {
                        _.each(a, function(v) {
                            var newObj = {};
                            angular.copy(v, newObj);
                            delete newObj["$$hashKey"];
                            b.push(newObj);
                        });
                    } else {
                        var newObj = {};
                        angular.copy(a, newObj);
                        delete newObj["$$hashKey"];
                        b.push(newObj);
                    }
                }
                function removeAfromB(a, b) {
                    if (_.isArray(a)) {
                        _.each(a, function(v, i) {
                            _.each(b, function(o, i) {
                                if (_(v).isEqual(o)) {
                                    b.splice(i, 1);
                                }
                            });
                        });
                    } else {
                        _.each(b, function(o, i) {
                            if (_(a).isEqual(o)) {
                                b.splice(0, 1);
                            }
                        });
                    }
                }
                $scope.moveToRigth = function(leftModel, deleteOriginal) {
                    movingAtoB(leftModel, $scope.rightItems);
                    if (deleteOriginal) {
                        removeAfromB(leftModel, $scope.leftItems);
                        $scope.leftModel = null;
                    }
                };
                $scope.moveToLeft = function(rightModel, deleteOriginal) {
                    movingAtoB(rightModel, $scope.leftItems);
                    if (deleteOriginal) {
                        removeAfromB(rightModel, $scope.rightItems);
                        $scope.rightModel = null;
                    }
                };
            },
            restrict: "A",
            link: function($scope, iElm, iAttrs, controller) {
                var getOptions = function() {
                    return angular.extend({
                        showCopyButton: true,
                        showMoveButton: true
                    }, uiItemSelectorConfig, $scope.$eval(iAttrs.ktItemSelector));
                };
                iElm.html(getTemplate(iAttrs.leftOptions, iAttrs.rightOptions));
                iElm.find("select").attr("size", $scope.leftItems.length);
                $scope.options = getOptions();
                $compile(iElm.contents())($scope);
            }
        };
    } ]);
    angular.module("kt.ui.grid", []).constant("uiGrid", {}).directive("ktGrid", [ "uiGrid", function() {
        return {
            scope: {
                data: "=",
                pagerData: "="
            },
            restrict: "ACM",
            link: function($scope, iElm, iAttrs, controller) {
                var options = angular.extend({}, {
                    gridHeight: iAttrs.gridHeight,
                    gridWidth: iAttrs.gridWidth,
                    pager: iAttrs.pager,
                    rowNum: iAttrs.rowNum,
                    rowNumWidth: iAttrs.rowNumWidth,
                    rowTotal: iAttrs.rowTotal,
                    forcefit: iAttrs.forcefit,
                    caption: iAttrs.caption,
                    sortable: iAttrs.sortable,
                    scroll: iAttrs.scroll,
                    autowidth: iAttrs.autowidth,
                    autoheight: iAttrs.autoheight
                }), colMeta, handlers, gridId = iElm.attr("id");
                function init() {
                    colMeta = buildColMeta(iElm);
                    handlers = buildHandlers(iElm);
                    iElm.empty();
                    if (options.pager === "true") {
                        iElm.after("<div id='" + gridId + "-pager'></div>");
                    }
                    jQuery(iElm).jqGrid({
                        data: $scope.data,
                        datatype: "local",
                        height: options.gridHeight ? Number(options.gridHeight) : undefined,
                        width: options.gridWidth ? Number(options.gridWidth) : "100%",
                        colNames: colMeta.colNames,
                        colModel: colMeta.colModel,
                        pager: options.pager === "true" ? "#" + gridId + "-pager" : null,
                        rowTotal: options.rowTotal ? Number(options.rowTotal) : 0,
                        rowNum: options.rowNum ? Number(options.rowNum) : -1,
                        rowList: [ 3, 6, 9 ],
                        scroll: options.scroll === "true" ? true : false,
                        sortable: options.sortable === "true" ? true : false,
                        sortname: options.sortname,
                        sortorder: options.sortorder,
                        autowidth: options.autowidth === "true" ? true : false,
                        autoheight: options.autoheight === "true" ? true : false,
                        imgpath: options.imgpath,
                        viewrecords: true,
                        rownumbers: options.rownumbers === "true" ? true : false,
                        rowNumWidth: Number(options.rowNumWidth) || 10,
                        forceFit: options.forcefit === "true" ? true : false,
                        onSelectRow: handlers.onSelectRow,
                        onPaging: handlers.onPageChange,
                        caption: options.caption
                    });
                    jQuery(window).bind("resize", function() {
                        var width = jQuery(iElm).parent().parent().parent().parent().parent().width();
                        jQuery(iElm).jqGrid("setGridWidth", width);
                    });
                    $scope.$watch("data", function(newScopeData, oldScopeData) {
                        var msg = options.noDataMsg || "조회된 데이터가 없습니다.";
                        var jqGrid = jQuery(iElm).jqGrid();
                        jQuery(iElm).jqGrid("clearGridData", true);
                        if (newScopeData.rows === undefined || newScopeData.rows === null || newScopeData.rows.length === undefined || newScopeData.rows.length === 0) {
                            if (jQuery("#" + gridId + "-emptyMsg").length === 0) jQuery(iElm).parent().append("<div id='" + gridId + "-emptyMsg' style=\"font-size:'8pt';text-align:center;padding:'10px';height:'auto'\">" + msg + "<div>");
                        } else {
                            jQuery("#" + gridId + "-emptyMsg").remove();
                            jqGrid[0].addJSONData(newScopeData);
                        }
                    }, true);
                }
                function getHandlerMethodNameMap(el) {
                    var $tr = el.children("tbody").children("tr");
                    return {
                        onSelectRowFuncName: $tr.attr("on-select-row") || ""
                    };
                }
                function buildColMeta(el) {
                    var cn = [], cm = [], ci = [];
                    angular.forEach(el.children("thead").children("tr").children("th"), function(value, key) {
                        cn.push(value.innerHTML);
                    });
                    angular.forEach(el.children("tbody").children("tr").children("td"), function(tdEl) {
                        var $td = angular.element(tdEl), expc = $td.html();
                        name = expc.replace(/[{}|<>\s]/g, ""), title = $td.attr("title") || name, index = $td.attr("index") || name, 
                        width = $td.attr("width") || 100, sorttype = $td.attr("sorttype") || null, sortable = $td.attr("sortable") === "true" ? true : false, 
                        align = $td.attr("align") || null;
                        if (sortable) {
                            if (sorttype === null) {
                                throw Error(name + "컬럼에 sortable이 ture인데 sorttype이 정의되지 않았습니다.");
                                return;
                            }
                        }
                        ci.push(index);
                        cm.push({
                            name: name,
                            index: index,
                            width: width,
                            align: align,
                            sortable: sortable,
                            sorttype: sorttype
                        });
                    });
                    return {
                        colNames: cn,
                        colModel: cm,
                        colIndexs: ci
                    };
                }
                function buildHandlers() {
                    var handlerMethodNameMap = getHandlerMethodNameMap(iElm), controllerName = handlerMethodNameMap.onSelectRowFuncName.replace(/\(.*\)/, ""), pageChangeName = iAttrs.onPageChange ? iAttrs.onPageChange.replace(/\(.*\)/, "") : null, controllerFunc = $scope.$parent[controllerName];
                    pageChangeFunc = $scope.$parent[pageChangeName];
                    return {
                        onSelectRow: function(id) {
                            var rowData = jQuery(iElm).jqGrid("getRowData", id);
                            setTimeout(function() {
                                if (controllerFunc) {
                                    controllerFunc.apply($scope.$parent, [ $scope.data.rows[Number(id) - 1], rowData ]);
                                    $scope.$apply();
                                }
                            });
                        },
                        onPageChange: function(name) {
                            var jqGrid = jQuery(iElm).jqGrid();
                            setTimeout(function() {
                                var page = jqGrid.getGridParam("page");
                                var rowNum = jqGrid.getGridParam("rowNum");
                                if ($scope.pagerData) {
                                    $scope.pagerData.page = page;
                                    $scope.pagerData.rowNum = Number(rowNum);
                                }
                                if (pageChangeFunc) {
                                    pageChangeFunc.apply($scope.$parent, [ page, Number(rowNum) ]);
                                    $scope.$apply();
                                }
                            });
                        }
                    };
                }
                init();
            }
        };
    } ]);
    angular.module("kt.ui.map", []).directive("infowindow", [ "$compile", function($compile) {
        return {
            require: "^ktMap",
            restrict: "AE",
            controller: function($scope, $element, $attrs) {},
            link: function($scope, iElm, iAttrs, controller) {
                var elText = iElm.html();
                var newInfoWindow = new olleh.maps.InfoWindow({
                    cBox: true,
                    content: elText,
                    maxWidth: iAttrs.maxWidth ? new Number(iAttrs.maxWidth) : undefined
                });
                var d = newInfoWindow.open;
                newInfoWindow.open = function(a, b, c) {
                    d.apply(newInfoWindow, [ a, b, c ]);
                    $compile(newInfoWindow.childDiv)($scope.$parent);
                };
                $scope.infowindows.push(newInfoWindow);
                $scope.$parent[iAttrs.infowindow] = newInfoWindow;
            }
        };
    } ]).directive("ktMap", [ "$compile", function($compile) {
        return {
            scope: {
                center: "=",
                markers: "="
            },
            controller: function($scope, $element, $attrs) {
                $scope.infowindows = [];
            },
            restrict: "EA",
            link: function($scope, iElm, iAttrs, controller) {
                if ($scope.center === undefined) {
                    throw new Error("center 값이 없습니다. center를 전달해 주세요.");
                }
                var wrapperTemplate = '<div class="mapWrapper"> 								<div> 									<div class="canvas_map" style="position:absolute; width:100%; height:100%; left:0px; top:0px"></div> 								</div> 							</div>';
                iElm.html(wrapperTemplate);
                var srcproj = new olleh.maps.Projection("UTM_K");
                var destproj = new olleh.maps.Projection("EPSG:4326");
                var point = new olleh.maps.Point($scope.center.x, $scope.center.y);
                olleh.maps.Projection.transform(point, destproj, srcproj);
                var map = new olleh.maps.Map(iElm.find(".canvas_map").get()[0], {
                    center: new olleh.maps.Coord(point.x, point.y),
                    zoom: new Number(iAttrs.zoom) || 5,
                    mapTypeId: olleh.maps.MapTypeId.BASEMAP,
                    disableDefaultUI: true
                });
                $scope.$watch("markers", function(newMarkers) {
                    _(newMarkers).each(function(value, key, newMarkers) {
                        var srcproj = new olleh.maps.Projection(value.srcproj);
                        var destproj = new olleh.maps.Projection(value.destproj);
                        var point = new olleh.maps.Point(value.x, value.y);
                        olleh.maps.Projection.transform(point, destproj, srcproj);
                        var newMark = new olleh.maps.Marker({
                            position: new olleh.maps.Coord(point.x, point.y),
                            map: map,
                            icon: new olleh.maps.MarkerImage(value.image, new olleh.maps.Size(value.size[0], value.size[1]), new olleh.maps.Pixel(0, 0), new olleh.maps.Pixel(0, 0))
                        });
                        if (value.onClick) {
                            olleh.maps.event.addListener(newMark, "click", function() {
                                $scope.$apply(function() {
                                    value.onClick.apply(null, [ newMark ]);
                                });
                            });
                        }
                        $scope.$parent[value.instance] = newMark;
                    });
                }, true);
                $scope.$watch("center", function(newCenter) {
                    var point = new olleh.maps.Point(newCenter.x, newCenter.y);
                    olleh.maps.Projection.transform(point, destproj, srcproj);
                    map.setCenter(new olleh.maps.Coord(point.x, point.y));
                }, true);
                $scope.$parent[iAttrs.ktMap] = map;
                $compile(iElm.contents())($scope);
            }
        };
    } ]);
    angular.module("kt.ui.modal", [ "kt.ui.dialog" ]).directive("ktModal", [ "$parse", "$dialog", function($parse, $dialog) {
        return {
            restrict: "EA",
            terminal: true,
            link: function(scope, elm, attrs) {
                var opts = angular.extend({}, scope.$eval(attrs.uiOptions || attrs.bsOptions || attrs.options));
                var shownExpr = attrs.ktModal || attrs.show;
                var setClosed;
                opts = angular.extend(opts, {
                    template: elm.html(),
                    resolve: {
                        $scope: function() {
                            return scope;
                        }
                    }
                });
                var dialog = $dialog.dialog(opts);
                elm.remove();
                if (attrs.close) {
                    setClosed = function() {
                        $parse(attrs.close)(scope);
                    };
                } else {
                    setClosed = function() {
                        if (angular.isFunction($parse(shownExpr).assign)) {
                            $parse(shownExpr).assign(scope, false);
                        }
                    };
                }
                scope.$watch(shownExpr, function(isShown, oldShown) {
                    if (isShown) {
                        dialog.open().then(function() {
                            setClosed();
                        });
                    } else {
                        if (dialog.isOpen()) {
                            dialog.close();
                        }
                    }
                });
            }
        };
    } ]);
    angular.module("kt.ui.navigation", []).directive("ktNavigation", function($location) {
        "use strict";
        return {
            restrict: "A",
            link: function postLink(scope, element, attrs, controller) {
                element.addClass("kt-navigation");
                scope.$watch(function() {
                    return $location.path();
                }, function(newValue, oldValue) {
                    $("li[data-match-route]", element).each(function(k, li) {
                        var $li = angular.element(li), pattern = $li.attr("data-match-route"), regexp = new RegExp("^" + pattern + "$", [ "i" ]);
                        if (regexp.test(newValue)) {
                            $li.addClass("active");
                        } else {
                            $li.removeClass("active");
                        }
                    });
                });
            }
        };
    }).directive("ktDropdown", function($parse, $compile, $timeout) {
        var buildTemplate = function(items, ul, width) {
            if (!ul) ul = [ '<ul class="dropdown-menu" role="menu" aria-labelledby="drop1"><div class="white-line" style="width:' + width + 'px; border: 1px solid #fff; position: absolute; top: -1px;"></div>', "</ul>" ];
            angular.forEach(items, function(item, index) {
                if (item.divider) return ul.splice(index + 1, 0, '<li class="divider"></li>');
                var li = "<li" + (item.submenu && item.submenu.length ? ' class="dropdown-submenu"' : "") + ">" + '<a tabindex="-1" ng-href="' + (item.href || "") + '"' + (item.click ? '" ng-click="' + item.click + '"' : "") + (item.target ? '" target="' + item.target + '"' : "") + (item.method ? '" data-method="' + item.method + '"' : "") + ">" + (item.text || "") + "</a>";
                if (item.submenu && item.submenu.length) li += buildTemplate(item.submenu).join("\n");
                li += "</li>";
                ul.splice(index + 1, 0, li);
            });
            return ul;
        };
        return {
            restrict: "EA",
            scope: true,
            link: function postLink(scope, iElement, iAttrs) {
                var getter = $parse(iAttrs.ktDropdown), items = getter(scope);
                $timeout(function() {
                    if (!angular.isArray(items)) {}
                    var dropdown = angular.element(buildTemplate(items, null, $(iElement).outerWidth()).join(""));
                    dropdown.insertAfter(iElement);
                    $compile(iElement.next("ul.dropdown-menu"))(scope);
                    $(iElement).on("click", function(a, b) {
                        $(iElement).next().find(".white-line").width($(iElement.get()).outerWidth() - 4);
                    });
                });
                iElement.addClass("dropdown-toggle").attr("data-toggle", "dropdown");
            }
        };
    });
    angular.module("kt.ui.progressbar", [ "kt.ui.service.transition" ]).constant("progressConfig", {
        animate: true,
        autoType: false,
        stackedTypes: [ "success", "info", "warning", "danger" ]
    }).controller("ProgressBarController", [ "$scope", "$attrs", "progressConfig", function($scope, $attrs, progressConfig) {
        var animate = angular.isDefined($attrs.animate) ? $scope.$eval($attrs.animate) : progressConfig.animate;
        var autoType = angular.isDefined($attrs.autoType) ? $scope.$eval($attrs.autoType) : progressConfig.autoType;
        var stackedTypes = angular.isDefined($attrs.stackedTypes) ? $scope.$eval("[" + $attrs.stackedTypes + "]") : progressConfig.stackedTypes;
        this.makeBar = function(newBar, oldBar, index) {
            var newValue = angular.isObject(newBar) ? newBar.value : newBar || 0;
            var oldValue = angular.isObject(oldBar) ? oldBar.value : oldBar || 0;
            var type = angular.isObject(newBar) && angular.isDefined(newBar.type) ? newBar.type : autoType ? getStackedType(index || 0) : null;
            return {
                from: oldValue,
                to: newValue,
                type: type,
                animate: animate
            };
        };
        function getStackedType(index) {
            return stackedTypes[index];
        }
        this.addBar = function(bar) {
            $scope.bars.push(bar);
            $scope.totalPercent += bar.to;
        };
        this.clearBars = function() {
            $scope.bars = [];
            $scope.totalPercent = 0;
        };
        this.clearBars();
    } ]).directive("progress", function() {
        return {
            restrict: "EA",
            replace: true,
            controller: "ProgressBarController",
            scope: {
                value: "=",
                onFull: "&",
                onEmpty: "&"
            },
            template: '<div class="progress">        <progressbar ng-repeat="bar in bars" width="bar.to" old="bar.from" animate="bar.animate" type="bar.type">        </progressbar>        </div>',
            link: function(scope, element, attrs, controller) {
                scope.$watch("value", function(newValue, oldValue) {
                    controller.clearBars();
                    if (angular.isArray(newValue)) {
                        for (var i = 0, n = newValue.length; i < n; i++) {
                            controller.addBar(controller.makeBar(newValue[i], oldValue[i], i));
                        }
                    } else {
                        controller.addBar(controller.makeBar(newValue, oldValue));
                    }
                }, true);
                scope.$watch("totalPercent", function(value) {
                    if (value >= 100) {
                        scope.onFull();
                    } else if (value <= 0) {
                        scope.onEmpty();
                    }
                }, true);
            }
        };
    }).directive("progressbar", [ "$transition", function($transition) {
        return {
            restrict: "EA",
            replace: true,
            scope: {
                width: "=",
                old: "=",
                type: "=",
                animate: "="
            },
            template: "<div class='bar' ng-class='type && \"bar-\" + type'></div>",
            link: function(scope, element) {
                scope.$watch("width", function(value) {
                    if (scope.animate) {
                        element.css("width", scope.old + "%");
                        $transition(element, {
                            width: value + "%"
                        });
                    } else {
                        element.css("width", value + "%");
                    }
                });
            }
        };
    } ]);
    angular.module("kt.ui.tab", []).controller("TabsController", [ "$scope", "$element", function($scope, $element) {
        var panes = $scope.panes = [];
        this.select = $scope.select = function selectPane(pane) {
            angular.forEach(panes, function(pane) {
                pane.selected = false;
            });
            pane.selected = true;
        };
        this.addPane = function addPane(pane) {
            if (!panes.length) {
                $scope.select(pane);
            }
            panes.push(pane);
        };
        this.removePane = function removePane(pane) {
            var index = panes.indexOf(pane);
            panes.splice(index, 1);
            if (pane.selected && panes.length > 0) {
                $scope.select(panes[index < panes.length ? index : index - 1]);
            }
        };
    } ]).directive("ktTab", function() {
        return {
            restrict: "EA",
            transclude: true,
            scope: {},
            controller: "TabsController",
            template: '<div class="tabbable">                 <ul class="nav nav-tabs">                   <li ng-repeat="pane in panes" ng-class="{active:pane.selected}">                     <a ng-click="select(pane)">{{pane.heading}}</a>                   </li>                 </ul>                 <div class="tab-content" ng-transclude></div>               </div>\n              ',
            replace: true
        };
    }).directive("ktPanel", [ "$parse", function($parse) {
        return {
            require: "^ktTab",
            restrict: "EA",
            transclude: true,
            scope: {
                heading: "@"
            },
            link: function(scope, element, attrs, tabsCtrl) {
                var getSelected, setSelected;
                scope.selected = false;
                if (attrs.active) {
                    getSelected = $parse(attrs.active);
                    setSelected = getSelected.assign;
                    scope.$watch(function watchSelected() {
                        return getSelected(scope.$parent);
                    }, function updateSelected(value) {
                        scope.selected = value;
                    });
                    scope.selected = getSelected ? getSelected(scope.$parent) : false;
                }
                scope.$watch("selected", function(selected) {
                    if (selected) {
                        tabsCtrl.select(scope);
                    }
                    if (setSelected) {
                        setSelected(scope.$parent, selected);
                    }
                });
                tabsCtrl.addPane(scope);
                scope.$on("$destroy", function() {
                    tabsCtrl.removePane(scope);
                });
            },
            template: '<div class="tab-pane" ng-class="{active: selected}" ng-show="selected" ng-transclude></div>\n',
            replace: true
        };
    } ]);
    angular.module("kt.ui.table.pagination", []).constant("tablePaginationConfig", {
        boundaryLinks: false,
        directionLinks: true,
        firstText: "First",
        previousText: "Previous",
        nextText: "Next",
        lastText: "Last"
    }).directive("pagination", [ "tablePaginationConfig", function(paginationConfig) {
        return {
            restrict: "EA",
            scope: {
                numPages: "=",
                currentPage: "=",
                maxSize: "=",
                onSelectPage: "&"
            },
            template: '<div class="pagination"><ul>' + '<li ng-repeat="page in pages" ng-class="{active: page.active, disabled: page.disabled}"><a ng-click="selectPage(page.number)">{{page.text}}</a></li>' + "</ul>" + "</div>",
            replace: true,
            link: function(scope, element, attrs) {
                var boundaryLinks = angular.isDefined(attrs.boundaryLinks) ? scope.$eval(attrs.boundaryLinks) : paginationConfig.boundaryLinks;
                var directionLinks = angular.isDefined(attrs.directionLinks) ? scope.$eval(attrs.directionLinks) : paginationConfig.directionLinks;
                var firstText = angular.isDefined(attrs.firstText) ? attrs.firstText : paginationConfig.firstText;
                var previousText = angular.isDefined(attrs.previousText) ? attrs.previousText : paginationConfig.previousText;
                var nextText = angular.isDefined(attrs.nextText) ? attrs.nextText : paginationConfig.nextText;
                var lastText = angular.isDefined(attrs.lastText) ? attrs.lastText : paginationConfig.lastText;
                function makePage(number, text, isActive, isDisabled) {
                    return {
                        number: number,
                        text: text,
                        active: isActive,
                        disabled: isDisabled
                    };
                }
                scope.$watch("numPages + currentPage + maxSize", function() {
                    scope.pages = [];
                    var startPage = 1, endPage = scope.numPages;
                    if (scope.maxSize && scope.maxSize < scope.numPages) {
                        startPage = Math.max(scope.currentPage - Math.floor(scope.maxSize / 2), 1);
                        endPage = startPage + scope.maxSize - 1;
                        if (endPage > scope.numPages) {
                            endPage = scope.numPages;
                            startPage = endPage - scope.maxSize + 1;
                        }
                    }
                    for (var number = startPage; number <= endPage; number++) {
                        var page = makePage(number, number, scope.isActive(number), false);
                        scope.pages.push(page);
                    }
                    if (directionLinks) {
                        var previousPage = makePage(scope.currentPage - 1, previousText, false, scope.noPrevious());
                        scope.pages.unshift(previousPage);
                        var nextPage = makePage(scope.currentPage + 1, nextText, false, scope.noNext());
                        scope.pages.push(nextPage);
                    }
                    if (boundaryLinks) {
                        var firstPage = makePage(1, firstText, false, scope.noPrevious());
                        scope.pages.unshift(firstPage);
                        var lastPage = makePage(scope.numPages, lastText, false, scope.noNext());
                        scope.pages.push(lastPage);
                    }
                    if (scope.currentPage > scope.numPages) {
                        scope.selectPage(scope.numPages);
                    }
                });
                scope.noPrevious = function() {
                    return scope.currentPage === 1;
                };
                scope.noNext = function() {
                    return scope.currentPage === scope.numPages;
                };
                scope.isActive = function(page) {
                    return scope.currentPage === page;
                };
                scope.selectPage = function(page) {
                    if (!scope.isActive(page) && page > 0 && page <= scope.numPages) {
                        scope.currentPage = page;
                        scope.onSelectPage({
                            page: page
                        });
                    }
                };
            }
        };
    } ]);
    angular.module("kt.ui.table", []).directive("ktTable", [ "$compile", "$q", "$parse", "$http", "ktTableParams", function($compile, $q, $parse, $http, ktTableParams) {
        "use strict";
        return {
            restrict: "A",
            priority: 1001,
            scope: true,
            controller: [ "$scope", "$timeout", function($scope, $timeout) {
                var updateParams;
                $scope.params = $scope.params || {
                    page: 1,
                    count: 10
                };
                $scope.$watch("params.filter", function(value) {
                    if ($scope.params.$liveFiltering) {
                        return updateParams(value);
                    }
                }, true);
                updateParams = function(newParams) {
                    newParams = angular.extend($scope.params, newParams);
                    $scope.paramsModel.assign($scope.$parent, new ktTableParams(newParams));
                    return $scope.params = angular.copy(newParams);
                };
                $scope.goToPage = function(page) {
                    if (page > 0 && $scope.params.page !== page && $scope.params.count * (page - 1) <= $scope.params.total) {
                        return updateParams({
                            page: page
                        });
                    }
                };
                $scope.changeCount = function(count) {
                    return updateParams({
                        page: 1,
                        count: count
                    });
                };
                $scope.doFilter = function() {
                    return updateParams({
                        page: 1
                    });
                };
                return $scope.sortBy = function(column) {
                    var sorting, sortingParams;
                    if (!column.sortable) {
                        return;
                    }
                    sorting = $scope.params.sorting && $scope.params.sorting[column.sortable] && $scope.params.sorting[column.sortable] === "desc";
                    sortingParams = {};
                    sortingParams[column.sortable] = sorting ? "asc" : "desc";
                    return updateParams({
                        sorting: sortingParams
                    });
                };
            } ],
            compile: function(element, attrs) {
                var columns, i;
                i = 0;
                columns = [];
                angular.forEach(element.find("tr[ng-repeat] td"), function(item) {
                    var el;
                    el = $(item);
                    return columns.push({
                        id: i++,
                        title: el.attr("title") || el.text(),
                        sortable: el.attr("sortable") ? el.attr("sortable") : false,
                        filter: el.attr("filter") ? $parse(el.attr("filter"))() : false,
                        filterData: el.attr("filter-data") ? el.attr("filter-data") : null
                    });
                });
                return function(scope, element, attrs) {
                    var generatePages, headerTemplate, paginationTemplate;
                    scope.columns = columns;
                    generatePages = function(currentPage, totalItems, pageSize) {
                        var maxBlocks, maxPage, maxPivotPages, minPage, numPages, pages;
                        maxBlocks = 11;
                        pages = [];
                        numPages = Math.ceil(totalItems / pageSize);
                        if (numPages > 1) {
                            pages.push({
                                type: "prev",
                                number: Math.max(1, currentPage - 1),
                                active: currentPage > 1
                            });
                            pages.push({
                                type: "first",
                                number: 1,
                                active: currentPage > 1
                            });
                            maxPivotPages = Math.round((maxBlocks - 5) / 2);
                            minPage = Math.max(2, currentPage - maxPivotPages);
                            maxPage = Math.min(numPages - 1, currentPage + maxPivotPages * 2 - (currentPage - minPage));
                            minPage = Math.max(2, minPage - (maxPivotPages * 2 - (maxPage - minPage)));
                            i = minPage;
                            while (i <= maxPage) {
                                if (i === minPage && i !== 2 || i === maxPage && i !== numPages - 1) {
                                    pages.push({
                                        type: "more"
                                    });
                                } else {
                                    pages.push({
                                        type: "page",
                                        number: i,
                                        active: currentPage !== i
                                    });
                                }
                                i++;
                            }
                            pages.push({
                                type: "last",
                                number: numPages,
                                active: currentPage !== numPages
                            });
                            pages.push({
                                type: "next",
                                number: Math.min(numPages, currentPage + 1),
                                active: currentPage < numPages
                            });
                        }
                        return pages;
                    };
                    scope.$parent.$watch(attrs.ktTable, function(params) {
                        if (angular.isUndefined(params)) {
                            return;
                        }
                        scope.paramsModel = $parse(attrs.ktTable);
                        scope.pages = generatePages(params.page, params.total, params.count);
                        return scope.params = angular.copy(params);
                    }, true);
                    if (attrs.showFilter) {
                        scope.$parent.$watch(attrs.showFilter, function(value) {
                            return scope.show_filter = value;
                        });
                    }
                    angular.forEach(columns, function(column) {
                        var promise;
                        if (!column.filterData) {
                            return;
                        }
                        promise = $parse(column.filterData)(scope, {
                            $column: column
                        });
                        if (!(angular.isObject(promise) && angular.isFunction(promise.then))) {
                            throw new Error("Function " + column.filterData + " must be promise");
                        }
                        delete column["filterData"];
                        return promise.then(function(data) {
                            if (!angular.isArray(data)) {
                                data = [];
                            }
                            data.unshift({
                                title: "-",
                                id: ""
                            });
                            return column.data = data;
                        });
                    });
                    if (!element.hasClass("kt-table")) {
                        scope.templates = {
                            header: attrs.templateHeader ? attrs.templateHeader : "tmpl/kt-table/header.html",
                            pagination: attrs.templatePagination ? attrs.templatePagination : "tmpl/kt-table/pager.html"
                        };
                        headerTemplate = $compile('<thead ng-include="templates.header"></thead>')(scope);
                        element.filter("thead").remove();
                        element.prepend(headerTemplate).addClass("kt-table");
                        return element;
                    }
                };
            }
        };
    } ]);
    angular.module("kt.ui.table").factory("ktTableParams", function() {
        var __hasProp = {}.hasOwnProperty;
        var isNumber, ktTableParams;
        isNumber = function(n) {
            return !isNaN(parseFloat(n)) && isFinite(n);
        };
        ktTableParams = function(data) {
            var ignoreFields, key, lastKey, name, params, v, value, _i, _len, _ref;
            ignoreFields = [ "total", "counts" ];
            this.page = 1;
            this.count = 1;
            this.counts = [ 10, 25, 50, 100 ];
            this.filter = {};
            this.sorting = {};
            for (key in data) {
                value = data[key];
                if (key.indexOf("[") >= 0) {
                    params = key.split(/\[(.*)\]/);
                    lastKey = "";
                    _ref = params.reverse();
                    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                        name = _ref[_i];
                        if (name !== "") {
                            v = value;
                            value = {};
                            value[lastKey = name] = isNumber(v) ? parseFloat(v) : v;
                        }
                    }
                    this[lastKey] = angular.extend(this[lastKey] || {}, value[lastKey]);
                } else {
                    this[key] = isNumber(data[key]) ? parseFloat(data[key]) : data[key];
                }
            }
            this.orderBy = function() {
                var column, direction, sorting, _ref1;
                sorting = [];
                _ref1 = this.sorting;
                for (column in _ref1) {
                    if (!__hasProp.call(_ref1, column)) continue;
                    direction = _ref1[column];
                    sorting.push((direction === "asc" ? "+" : "-") + column);
                }
                return sorting;
            };
            this.url = function(asString) {
                var item, pairs, pname, subkey;
                asString = asString || false;
                pairs = asString ? [] : {};
                for (key in this) {
                    if (this.hasOwnProperty(key)) {
                        if (ignoreFields.indexOf(key) >= 0) {
                            continue;
                        }
                        item = this[key];
                        name = encodeURIComponent(key);
                        if (typeof item === "object") {
                            for (subkey in item) {
                                if (!angular.isUndefined(item[subkey]) && item[subkey] !== "") {
                                    pname = name + "[" + encodeURIComponent(subkey) + "]";
                                    if (asString) {
                                        pairs.push(pname + "=" + encodeURIComponent(item[subkey]));
                                    } else {
                                        pairs[pname] = encodeURIComponent(item[subkey]);
                                    }
                                }
                            }
                        } else if (!angular.isFunction(item) && !angular.isUndefined(item) && item !== "") {
                            if (asString) {
                                pairs.push(name + "=" + encodeURIComponent(item));
                            } else {
                                pairs[name] = encodeURIComponent(item);
                            }
                        }
                    }
                }
                return pairs;
            };
            return this;
        };
        return ktTableParams;
    });
    angular.module("kt.ui.tree", []).controller("treeCtrl", [ "$scope", "$element", function($scope, $element) {
        var previousTarget = null;
        var previousClicked = null;
        var c = 0;
        $scope.toggle = function(treeData) {
            treeData.show = !treeData.show;
        };
        $scope.select = function(data, $event) {
            var dataToPass = angular.copy(data, {});
            dataToPass = omitHash(dataToPass);
            if (previousClicked !== null) $(previousClicked).removeClass("kt-tree-clicked");
            previousClicked = $event.currentTarget;
            $($event.currentTarget).addClass("kt-tree-clicked");
            $scope.onSelect({
                selected: dataToPass,
                $event: $event
            });
        };
        function omitHash(data) {
            var hashOmittedData;
            hashOmittedData = _.omit(data, "$$hashKey");
            if (data.children.length > 0) angular.forEach(data.children, function(value, key) {
                data.children[key] = omitHash(value);
            });
            return hashOmittedData;
        }
        $scope.hover = function($event) {
            if (previousTarget !== null) $(previousTarget).removeClass("kt-tree-hovered");
            previousTarget = $event.currentTarget;
            $($event.currentTarget).addClass("kt-tree-hovered");
        };
        $scope.showChildren = function(treeData) {
            return treeData.show && treeData.children.length > 0;
        };
        $scope.isSelected = function(data) {
            var selected = false;
            if (angular.equals($scope.selected, data)) {
                selected = true;
            }
            return {
                selected: selected
            };
        };
        $scope.addClass = function(treeData, isLast) {
            var cssOpts = {
                "kt-tree-open": treeData.show,
                "kt-tree-closed": !treeData.show,
                "kt-tree-leaf": treeData.children.length > 0 ? false : true,
                "kt-tree-last": isLast
            };
            if (treeData.level) cssOpts["kt-tree-level-" + treeData.level] = true;
            return cssOpts;
        };
    } ]).directive("ktTree", function() {
        return {
            restrict: "EA",
            scope: {
                data: "=",
                onSelect: "&"
            },
            controller: "treeCtrl",
            template: '<script type="text/ng-template"  id="tree_item_renderer.html"> 	    	<ins ng-click="toggle(treeData)" class="kt-tree-icon kt-tree-ocl">&nbsp;</ins> 	    	<a href="" ng-click="select(treeData, $event)" ng-mouseover="hover($event)"> 	    		<ins class="kt-tree-icon kt-tree-themeicon">&nbsp;</ins>{{treeData.text}} 	    	</a> 		    <ul> 		        <li ng-repeat="treeData in treeData.children" ng-class="addClass(treeData, $last)" ng-include="\'tree_item_renderer.html\'"></li> 		    </ul> 			</script> 			<div class="kt-tree kt-tree-default">\n			<ul class="unstyled">			    <li ng-repeat="treeData in data" ng-class="addClass(treeData, $last)" ng-include="\'tree_item_renderer.html\'"></li>			</ul></div>',
            link: function($scope, element, attrs, treeCtrl) {}
        };
    });
})({}, function() {
    return this;
}());
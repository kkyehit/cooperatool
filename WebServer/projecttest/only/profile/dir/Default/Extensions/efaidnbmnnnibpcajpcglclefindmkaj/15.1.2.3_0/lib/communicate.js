/*************************************************************************
* ADOBE CONFIDENTIAL
* ___________________
*
*  Copyright 2015 Adobe Systems Incorporated
*  All Rights Reserved.
*
* NOTICE:  All information contained herein is, and remains
* the property of Adobe Systems Incorporated and its suppliers,
* if any.  The intellectual and technical concepts contained
* herein are proprietary to Adobe Systems Incorporated and its
* suppliers and are protected by all applicable intellectual property laws,
* including trade secret and or copyright laws.
* Dissemination of this information or reproduction of this material
* is strictly forbidden unless prior written permission is obtained
* from Adobe Systems Incorporated.
**************************************************************************/
function dependOn(){"use strict";return[require("util"),require("common"),require("analytics"),require("proxy")]}var def;require=function(e){"use strict";return e},def=window.define?window.define:function(e,t){"use strict";return t.apply(null,[{ajax:$.ajax.bind($)}])};var exports=acom_analytics={};def(dependOn(),(function(e,t,i,s){"use strict";var n,o,r=null,a={};for(n in r||(r=new function(){var n={},r={};this.proxy=s.proxy.bind(this),this.LOG=t.LOG,this.registerHandlers=function(t){e.extend(n,t)},this.registerModule=function(e,t){a[e]=t},this.getModule=function(e){return a[e]},this.reset=function(e,s){t.reset(e.environment),i.setAnalyticsUsage(e.analytics_on,s.tab.id),e.environment&&(i.environment=e.environment,i.event(i.e.OPTIONS_SET_ENV))},this.updateVisibility=function(e){if(1==e.received_limits&&1==e.received_signedin&&1==e.received_display){if("search"===e.frictionless_workflow){if(0==e.signed_in&&1==e.has_free_ops){let t=e.panel_op;e.panel_op="send-external-msg",e.data={valid:"true"},this.sendMessage(e),e.frame_visibility="visible",e.content_op="none",e.panel_op="load-frictionless",this.sendMessage(e),e.panel_op=t;let s=e.pdf_action?e.pdf_action.toUpperCase().replace(/\-/g,"_"):"UNKNOWN";i.event(i.e.FRICTIONLESS_WIDGET_LOADED,{TOOL:s,WORKFLOW:e.frictionless_workflow})}}else if(e.panel_op="load-frictionless",e.hide_spinner=!0,e.content_op="none",this.sendMessage(e),e.hide_spinner=null,0==e.signed_in&&1==e.has_free_ops){let t=e.pdf_action?e.pdf_action.toUpperCase().replace(/\-/g,"_"):"UNKNOWN";i.event(i.e.FRICTIONLESS_WIDGET_LOADED,{TOOL:t,WORKFLOW:e.frictionless_workflow})}e.received_limits=null,e.received_signedin=null,e.signed_in=null,e.has_free_ops=null,e.received_display=null}},this.calculateFrictionlessEventsTiming=function(e,t,i){switch(i){case"startup_to_iframe_load":t.startup_time&&(t.iframe_onload_time&&(e.startup_to_iframe_load=t.iframe_onload_time-t.startup_time,delete t.iframe_onload_time),t.iframe_call_time&&(e.startup_to_iframe_call=t.iframe_call_time-t.startup_time,delete t.iframe_call_time));break;case"signed_in_time":t.startup_time&&t.signed_in_time&&(e.startup_to_signin=t.signed_in_time-t.startup_time,delete t.signed_in_time);break;case"limits_time":t.startup_time&&t.limits_time&&(e.startup_to_limits=t.limits_time-t.startup_time,delete t.limits_time);break;case"dropzone_display_time":t.startup_time&&t.dropzone_display_time&&(e.startup_to_display=t.dropzone_display_time-t.startup_time,delete t.dropzone_display_time)}},this.cleanupTimingInfo=function(e){let t=r[e];delete t.startup_time,delete t.iframe_call_time,delete t.iframe_onload_time,delete t.signed_in_time,delete t.limits_time,delete t.dropzone_display_time},this.sendTimingInfo=function(t,i){if("false"===e.getCookie("logAnalytics"))return void this.cleanupTimingInfo(t.tabId);let s={content:"timing_info"};s.workflow=t.frictionless_workflow,this.calculateFrictionlessEventsTiming(s,t,i),t.data=s;let n=t.panel_op;t.panel_op="send-external-msg",e.sendMessage(t,this.globals),t.panel_op=n},this.externalMsgHandler=function(t,s,n){let o=t.data,a=r[s.tab.id];if("signed_in"===o.content_op){a.received_signedin=!0,a.signed_in_time=t.timeStamp,a.signed_in=o.is_signed_in,"search"===a.frictionless_workflow&&1==a.signed_in&&(a.content_op="dismiss",this.sendMessage(a)),this.updateVisibility(a),this.sendTimingInfo(a,"signed_in_time");let e=o.is_signed_in?"SignedIn":"SignedOut";i.event(i.e.FRICTIONLESS_USER_SIGNEDIN,{WORKFLOW:a.frictionless_workflow,RESULT:e})}else if("limits"===o.content_op){if(a.received_limits=!0,a.limits_time=t.timeStamp,a.pdf_action&&o.limits){a.has_free_ops=this.hasFreeOperations(a.pdf_action,o.limits);let e=a.has_free_ops?"UnderLimit":"OverLimit";"search"===a.frictionless_workflow&&0==a.has_free_ops&&(a.content_op="dismiss",this.sendMessage(a)),this.updateVisibility(a),this.sendTimingInfo(a,"limits_time"),i.event(i.e.FRICTIONLESS_CONVERSION_LIMITS,{WORKFLOW:a.frictionless_workflow,RESULT:e})}}else if("complete_conversion"===o.content_op)e.createTab(o.conversion_url),i.event(i.e.FRICTIONLESS_SWITCH_TAB,{WORKFLOW:a.frictionless_workflow}),a&&(a.content_op="dismiss",this.sendMessage(a));else if(o.dc_hosted_event){let e=o.dc_hosted_event;switch(e.event){case"pre-processing-error":case"processing-error":if(e.event_data){let t=e.event_data.title,i=e.event_data.description;a&&(a.error_title=t,a.error_description=i,a.content_op="none",a.panel_op="show-frictionless-error",this.sendMessage(a))}break;case"file-upload-start":a&&(a.panel_op="clear-frictionless-error",this.sendMessage(a));break;case"dropzone-displayed":a&&(a.received_display=!0,a.dropzone_display_time=t.timeStamp,this.updateVisibility(a),this.sendTimingInfo(a,"dropzone_display_time"));break;case"startup-error":{a&&("search"===a.frictionless_workflow?(a.content_op="dismiss",this.sendMessage(a)):"trefoil"===a.frictionless_workflow&&(a.panel_op="load-frictionless",a.hide_spinner=!0,a.content_op="none",this.sendMessage(a),a.hide_spinner=null));let e=a.pdf_action?a.pdf_action.toUpperCase().replace(/\-/g,"_"):"UNKNOWN";i.event(i.e.FRICTIONLESS_WIDGET_STARTUP_ERROR,{TOOL:e});break}}}delete t.timeStamp},this.registerHandlers({reset:this.proxy(this.reset),external_msg:this.proxy(this.externalMsgHandler)}),this.version=-1,this.cookieStatus=void 0,this.legacyShim=function(){return this.version<=1},this.setVersion=function(e){this.version=e},this.setCookieStatus=function(e){this.cookieStatus=e},this.getCookieStatus=function(){return this.cookieStatus},this.resetPersistPrefCount=function(){e.setCookie("persist-menu-closed",0,3650)},this.incrementPersistPrefCount=function(){var t=e.getCookie("persist-menu-closed");t<10&&"false"!==e.getCookie("always-show-pdf-menu")&&(t++,e.setCookie("persist-menu-closed",t,3650)),t>=10&&(e.setCookie("always-show-pdf-menu","false",3650),i.event(i.e.PERSIST_PDF_OPENPDF_PREF_OFF))},this.isEnablePersistMenu=function(){return"false"!==e.getCookie("always-show-pdf-menu")&&(e.getCookie("persist-menu-closed")<10?!this.legacyShim():"true"===e.getCookie("always-show-pdf-menu")&&(this.resetPersistPrefCount(),!0))},this.hasFreeOperations=function(e,t){let i=null;switch(e){case"word-to-pdf":case"ppt-to-pdf":case"jpg-to-pdf":case"excel-to-pdf":case"createpdf":i="create_pdf";break;case"compress-pdf":i="compress_pdf"}let s=t[i];return s&&s.can_process||!1},this.handler=function(s,a,l){var d;if(s&&(this.dump(s,"Communicate Handler receive: "),a&&a.tab&&(s.tabId=a.tab.id,o||(o=a.tab.id),s.main_op))){if("analytics"==s.main_op)return delete s.main_op,void i.logBrowserAnalytics(s);if("timing_info"===s.main_op){let t=r[s.tabId];s.startup_time&&this.cleanupTimingInfo(s.tabId),delete s.main_op;let i=s.timing_op;return delete s.timing_op,void(r[s.tabId]&&(r[s.tabId]=e.extend(r[s.tabId],s),i&&this.sendTimingInfo(t,i)))}if(r[s.tabId]&&r[s.tabId].suppress_frictionless&&(s.suppress_frictionless=r[s.tabId].suppress_frictionless),"third-party-cookies"==s.main_op)return this.setCookieStatus(s.cookies_status),(this.version===SETTINGS.READER_VER||0===this.version||1===this.version&&0==e.getNMHConnectionStatus())&&(s.cookies_status||(SETTINGS.FRICTIONLESS_ENABLED=!1,this.version===SETTINGS.READER_VER&&this.avoidUrl(a.tab.url)?this.disable(s.tabId):0===this.version||1===this.version&&e.getNMHConnectionStatus())),delete s.main_op,void delete s.status;if("relay_to_content"===s.main_op)return 1==s.newUI&&"dismiss"===s.content_op?(s.persist=!1,null!=s.tabId&&(r[s.tabId].persist=!1),this.incrementPersistPrefCount()):1==s.newUI&&"pdf_menu"===s.panel_op&&(this.isEnablePersistMenu()?null!=s.tabId&&(r[s.tabId].persist=!0):s.persist=!1,this.resetPersistPrefCount()),delete s.main_op,i.logBrowserAnalytics(s),void this.sendMessage(s);if("get-frictionless-url"===s.main_op)return delete s.main_op,s.frictionless_uri=t.getFrictionlessUri(),s.env=t.getEnv(),s.panel_op="load-frictionless",this.sendMessage(s),void i.logBrowserAnalytics(s);if(i.logBrowserAnalytics(s),d=s.main_op,delete s.main_op,"dismiss"===d&&this.closeDialog(),s.version=this.version,s.NMHConnStatus=e.getNMHConnectionStatus(),n[d])return i.setOp({preview:"Copy",image_preview:"Image",send:"Send",fillsign:"FillSign",export:"Export",acom:"GotoAcom",to_pdf:"ConvertToPdf"}[s.handleResult]),n[d](s,a,l);e.consoleLog("failed to find handler for: "+d)}},this.closeDialog=function(){e.isFF()&&this.globals.panel.hide()},this.echoRequest=function(t){var s,n,o;if(SETTINGS.CHROME_VERSION<SETTINGS.SUPPORTED_VERSION)this.getModule("session").newSession("data/js/options.html",!0,{});else{if(!this.avoidUrl(t.url)){if(e.isFF())s=this.lastRequest||{panel_op:"html_menu"};else{var a=t.id||t.tabId;r[a]&&("cancelled"!==(s=r[a]).current_status&&"pdf_opened"!==s.current_status&&"pdf_failure"!==s.current_status||(o=s.is_pdf,this.clearStatus(s),s.is_pdf=o),s.current_status&&(s.panel_op="status"),this,n=s.is_pdf?"pdf_menu":"html_menu",s.panel_op&&"load-frictionless"===s.panel_op&&(delete s.panel_op,i.event(i.e.FRICTIONLESS_WIDGET_CLOSED)),"resize_window"===s.content_op&&(delete s.content_op,delete s.window_height),s.panel_op=s.panel_op||n,"html_menu"===n&&(s.persist=!1),e.consoleLog("repeat cached request: "+s.panel_op))}return s}this.disable(t.id)}},this.echo=function(t){i.event(i.e.TREFOIL_CLICKED);var s=this.echoRequest(t);if(s){"search"===s.frictionless_workflow&&(s.suppress_frictionless=!0),s.trefoilClick=!0,s.frictionless_workflow=null,s.frame_visibility=null,s.persist=this.isEnablePersistMenu();let t=chrome.i18n.getMessage("@@ui_locale");s.locale=e.getFrictionlessLocale(t),this.sendMessage(s)}},this.setGlobals=function(e){this.globals=e},this.dump=function(t,i){var s,n=[i];for(s in t)t.hasOwnProperty(s)&&n.push("  "+s+": "+t[s]);e.consoleLog(n.join("\n"))},this.sendMessage=function(t){var s,n=t.tabId;this.dump(t,"Sending message:"),t.version=this.version,r[n]=e.extend(r[n],t),e.showFrictionlessMenu(t)&&(t.show_frictionless=!0),t.NMHConnStatus=e.getNMHConnectionStatus();let o=this.getCookieStatus();o&&(t.cookieStatus=o),"pdf_menu"===t.panel_op&&(t.trefoilClick?s=i.e.TREFOIL_PDF_FROM_CLICK:0==t.persist&&0!=t.version&&(s=i.e.TREFOIL_PDF_MENU_SHOWN)),"flickr"===t.panel_op&&(s=i.e.FLICKR_OFFER_SHOWN),"html_menu"===t.panel_op&&(s=i.e.TREFOIL_HTML_FROM_CLICK),s&&i.checkAndLogAnalytics(s),e.sendMessage(t,this.globals),delete t.trefoilClick},this.deferMessage=function(e){"undefined"==typeof setTimeout?this.sendMessage(e):setTimeout(this.proxy(this.sendMessage,e),0)},this.filenameFromUrl=function(e){var t=decodeURIComponent(e),i=(t=(t=t.split("?")[0].replace(/\S*\//,"")).replace(/[\?]\S*/,"")).split("/");return-1==(t=i[i.length-1]).toLowerCase().indexOf(".pdf")&&(t+=".pdf"),t},this.pdf_menu=function(t,s){var n;(n=r[s.tab.id]=e.extend(r[s.tab.id],{tabId:s.tab.id})).filename=this.filenameFromUrl(t.url),n.panel_op="pdf_menu",n.url=t.url,n.is_pdf=!0,n.isFillnSignEnabled=SETTINGS.FILL_N_SIGN_ENABLED,1==t.persist&&this.isEnablePersistMenu()?n.persist=!0:n.persist=!1,"false"!==e.getCookie("always-show-pdf-menu")&&(1==n.persist&&i.event(i.e.PERSIST_PDF_MENU_SHOWN),this.deferMessage(n))},this.loaded=function(t){r[t]=e.extend(r[t],{tabId:t,loaded:!0}),this.enable(t)},this.clearStatus=function(e,t){"in_progress"!==e.current_status&&"downloading"!==e.current_status&&(t||"waiting"!==e.current_status)&&(this.getModule("acro-web2pdf").cancelConversion(e.tabId),delete e.current_status,delete e.file_path,delete e.domtitle,delete e.timing,delete e.panel_op,delete e.is_pdf,delete e.trefoilUI,delete e.newUI)},this.loading=function(t){var i=t.id;r[i]=e.extend(r[i],{tabId:i,loaded:!1}),this.clearStatus(r[i],!0),this.enable(i)},this.active=function(t){if(o=t.tabId,r[o]=e.extend(r[o],{tabId:t.tabId}),this.isEnablePersistMenu()){var i=this.echoRequest(r[o]);i&&i.persist&&this.sendMessage(i)}},this.enable=function(e){var t=r[e].loaded?"data/images/acrobat_dc_appicon_24.png":"data/images/acrobat_dc_appicon_24_translucent.png";chrome.browserAction.setIcon({path:t,tabId:e}),r[e].loaded?chrome.browserAction.enable(e):chrome.browserAction.disable(e)},this.disable=function(e){r[e]&&(r[e].loaded=!1,this.enable(e))},this.close=function(e){this.getModule("acro-web2pdf").cancelConversion(e),delete r[e],o===e&&(o=null)},this.tabReplace=function(e,t){this.close(t),this.loaded(e)},this.activeTab=function(){return o},this.noop=function(){},this.avoidUrl=function(t){if(t=t||"",this.version===SETTINGS.ERP_READER_VER)return!0;if(t.startsWith("https://chrome.google.com"))return!0;if(this.version==SETTINGS.READER_VER&&0==SETTINGS.FRICTIONLESS_ENABLED)return!t.endsWith(".pdf")&&!t.endsWith(".PDF");if((0==this.version||1==this.version&&0==e.getNMHConnectionStatus())&&!1===SETTINGS.FRICTIONLESS_ENABLED)return!1;if(SETTINGS.FRICTIONLESS_ENABLED&&!t.endsWith(".pdf")&&!t.endsWith(".PDF")){const t=chrome.i18n.getMessage("@@ui_locale");if(null==e.getFrictionlessLocale(t)){if(this.version==SETTINGS.READER_VER)return!0;(0==this.version||1==this.version&&0==e.getNMHConnectionStatus())&&(SETTINGS.FRICTIONLESS_ENABLED=!1)}}return!t.startsWith("http")}},e.isChrome()&&(chrome.runtime.onMessage.addListener(r.proxy(r.handler)),chrome.tabs.onActivated.addListener(r.proxy(r.active)),chrome.tabs.onRemoved.addListener(r.proxy(r.close)),chrome.tabs.onCreated.addListener(r.proxy(r.loading)),chrome.tabs.onReplaced.addListener(r.proxy(r.tabReplace)))),r)r.hasOwnProperty(n)&&("function"==typeof r[n]?exports[n]=r[n].bind(r):exports[n]=r[n]);return r.registerHandlers({"send-analytics":r.proxy(r.noop)}),r}));
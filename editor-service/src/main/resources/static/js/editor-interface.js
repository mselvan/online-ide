var EditorInterface = function () {

};

EditorInterface.prototype = {
    init: function () {
        this.initEditor();
        this.bindCompile();
    },

    initEditor: function () {
        ace.require("ace/ext/language_tools");
        var editor = this.editor = ace.edit("editor");
        editor.setTheme("ace/theme/eclipse");
        editor.getSession().setMode("ace/mode/java");
        editor.setOptions({
            enableBasicAutocompletion: true,
            enableSnippets: true,
            enableLiveAutocompletion: false
        });
        editor.setOption("enableEmmet", true);
        editor.setValue($("#editor-template").text());
    },
    executionSuccess: function (data) {
        if(data.errors) {
            $("#consoleArea").text(data.errorString);
            $("#consoleArea").css("color", "red");
        } else {
            $("#consoleArea").text(data.result);
            $("#consoleArea").css("color", "black");
        }
    },
    executionFailure: function (error) {
        console.log(error);
    },
    bindCompile: function() {
        var self = this;
        $("#executeCode").on("click", function (event) {
            event.preventDefault();
            var data = {source : self.editor.getValue()};
            $.ajax({
                url: "/rest/java/execute",
                type: "POST",
                contentType: "server/json",
                data: JSON.stringify(data),
                success: self.executionSuccess,
                failure: self.executionFailure
            });
        });
    }

};

window.editorInterface = new EditorInterface();
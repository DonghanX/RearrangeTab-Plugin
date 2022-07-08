package com.donghanx.rearrange_tab

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.impl.EditorWindow

class CloseTabsWithSameSuffixAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val editorWindow = event.getData(EditorWindow.DATA_KEY) ?: return

        val selectedFileNameExt = editorWindow.selectedFile.extension ?: ""

        editorWindow.files.forEach {
            if (it.extension == selectedFileNameExt) {
                editorWindow.closeFile(it)
            }
        }

    }
}
package com.donghanx.rearrange_tab

import com.donghanx.rearrange_tab.ext.performWithRestoreSelectedFile
import com.donghanx.rearrange_tab.ext.sortByFileNameExt
import com.donghanx.rearrange_tab.ext.sortByParent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.vfs.VirtualFile

object SortActions {

    private inline fun handleBaseSortAction(
        event: AnActionEvent,
        sortAction: ((Array<VirtualFile>) -> Unit)
    ) {
        val project = event.project ?: return
        val editorWindow = event.getData(EditorWindow.DATA_KEY) ?: return

        val selectedFile = editorWindow.selectedFile

        val curTabs = editorWindow.files.also {
            sortAction.invoke(it)
        }

        FileEditorManagerEx.getInstance(project).also {

            it.performWithRestoreSelectedFile(selectedFile) { editorManager ->
                editorWindow.closeAllExcept(null)

                curTabs.forEach { file ->
                    editorManager.openFile(file, true)
                }

            }
        }
    }

    class TabSortBySuffixAction : AnAction() {

        override fun actionPerformed(event: AnActionEvent) {
            handleBaseSortAction(event) { fileArray ->
                fileArray.sortByFileNameExt()
            }
        }
    }

    class TabSortByParentAction : AnAction() {

        override fun actionPerformed(event: AnActionEvent) {
            handleBaseSortAction(event) { fileArray ->
                fileArray.sortByParent()
            }
        }
    }

}
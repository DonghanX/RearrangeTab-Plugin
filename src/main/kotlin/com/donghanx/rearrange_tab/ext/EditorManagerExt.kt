package com.donghanx.rearrange_tab.ext

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile

inline fun FileEditorManager.performWithRestoreSelectedFile(
    selectedFile: VirtualFile,
    actionCallback: ((FileEditorManager) -> Unit)
) {
    actionCallback.invoke(this)

    openFile(selectedFile, true)

    println("Restore previously selected file: $selectedFile")
}
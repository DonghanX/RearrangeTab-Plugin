package com.donghanx.rearrange_tab.ext

import com.intellij.openapi.vfs.VirtualFile

typealias FileArray = Array<VirtualFile>


/**
 * Sort strategy that groups editor tabs by their file name extension.
 *
 * TODO: Also sort the tabs in each group in alphabet order.
 */
fun FileArray.sortByFileNameExt() {
    sortWithCheck(this) { fileArray ->
        fileArray.sortBy { file ->
            file.extension
        }
    }
}

/**
 * Sort strategy that groups editor tabs by their parent.
 */
fun FileArray.sortByParent() {
    sortWithCheck(this) { fileArray ->
        fileArray.sortBy { file ->
            file.parent.name
        }
    }
}

private inline fun sortWithCheck(
    fileArray: FileArray,
    sortAction: ((FileArray) -> Unit)
) {
    if (fileArray.size <= 1) {
        return
    }

    sortAction.invoke(fileArray)

    // TODO: Add flags that indicate the state of sorting action
}


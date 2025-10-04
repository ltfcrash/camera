package com.hack.opensdk

import android.app.Application
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.net.Uri

object HackApi {

    private val application: Application
        get() {
            if (!::HackApplication.application.isInitialized) {
                throw IllegalStateException("HackApplication is not initialized")
            }
            return HackApplication.application
        }

    private val packageManager: PackageManager
        get() = application.packageManager

    fun getPackageInfo(packageName: String, userId: Int, flags: Int): PackageInfo? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(packageName, flags)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }

    fun getInstalledPackages(flags: Int, userId: Int): MutableList<String> {
        val packages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getInstalledPackages(flags)
        }
        return packages.map { it.packageName }.toMutableList()
    }

    fun installPackageFromHost(packageName: String, userId: Int, silent: Boolean): Int {
        // The original project performs a virtual install. In this open-source build we simply
        // report failure to indicate the operation is not supported.
        return 0
    }

    fun uninstallPackage(packageName: String, userId: Int) {
        // No-op in the open-source build.
    }

    fun deletePackageData(packageName: String, userId: Int) {
        // No-op in the open-source build.
    }

    fun getLaunchIntentForPackage(packageName: String, userId: Int): Intent {
        return packageManager.getLaunchIntentForPackage(packageName)
            ?: Intent(Intent.ACTION_VIEW, Uri.parse("package:$packageName"))
    }

    fun startActivity(intent: Intent, flags: Int): Int {
        val launchIntent = Intent(intent).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return try {
            application.startActivity(launchIntent)
            0
        } catch (_: Exception) {
            -1
        }
    }

    fun getAvailableUserSpace(): MutableList<Int> {
        // The original implementation exposes multiple virtual users. The open-source build only
        // supports the primary user.
        return mutableListOf(0)
    }
}

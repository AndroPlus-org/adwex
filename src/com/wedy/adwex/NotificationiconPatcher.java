package com.wedy.adwex;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class NotificationiconPatcher implements IXposedHookZygoteInit, IXposedHookInitPackageResources{

	private static final String PACKAGE_ESF = "org.adwfreak.launcher";
	private static String modulePath = null;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		if(!resparam.packageName.equals(PACKAGE_ESF)){
			return;
		}

		XModuleResources modRes = XModuleResources.createInstance(modulePath, resparam.res);
		resparam.res.setReplacement(PACKAGE_ESF, "string", "app_name", modRes.fwd(R.string.app_name));
	}

}

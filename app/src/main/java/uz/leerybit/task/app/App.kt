package uz.leerybit.task.app

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    initPluto()
  }

  private fun initPluto() {
    Pluto.Installer(this).addPlugin(PlutoNetworkPlugin("network"))
      .addPlugin(PlutoExceptionsPlugin("exceptions")).install()
  }

}


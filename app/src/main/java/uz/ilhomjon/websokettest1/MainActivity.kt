package uz.ilhomjon.websokettest1

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import uz.ilhomjon.websokettest1.databinding.ActivityMainBinding
import uz.ilhomjon.websokettest1.service.MyForegroundService
import uz.ilhomjon.websokettest1.utils.MyData

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val packageName = applicationContext.packageName
//
//        val intent = Intent()
//        intent.component = ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")
//        intent.putExtra("extra_pkgname", packageName)
//
//        if (packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isNotEmpty()) {
//            startActivity(intent)
//        } else {
//            Toast.makeText(this, "Xiaomi telefoni emas", Toast.LENGTH_SHORT).show()
//        }
//        val packageName = applicationContext.packageName
//        val intent = Intent()
//        intent.action = "miui.intent.action.APP_PERM_EDITOR"
//        intent.addCategory(Intent.CATEGORY_DEFAULT)
//        intent.putExtra("extra_pkgname", packageName)
//
//        try {
//            startActivity(intent)
//        } catch (e: Exception) {
//            // Ilovaning ruxsatlar sozlamalari ochilmadi
//        }



        binding.apply {

            MyData.liveData.observe(this@MainActivity){
                tvInfo.text = it
            }
            val serviceIntent = Intent(this@MainActivity, MyForegroundService::class.java)

            ContextCompat.startForegroundService(this@MainActivity, serviceIntent)
            btnStart.setOnClickListener {
                ContextCompat.startForegroundService(this@MainActivity, serviceIntent)
            }
            btnStop.setOnClickListener {
                val serviceIntent = Intent(this@MainActivity, MyForegroundService::class.java)
                stopService(serviceIntent)
            }
        }
    }


}
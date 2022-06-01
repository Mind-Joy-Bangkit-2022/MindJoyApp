package com.example.mindjoy.ui.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mindjoy.R
import com.example.mindjoy.databinding.ActivityCameraResultBinding
import com.example.mindjoy.ui.helper.rotateBitmap
import com.example.mindjoy.ui.helper.uriToFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class CameraResultActivity : AppCompatActivity() {
    companion object {
        const val CAMERA_X_RESULT = 200
    }

    private lateinit var binding: ActivityCameraResultBinding

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcherIntentCameraX
    }

    private val launcherIntentCameraX =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == CAMERA_X_RESULT) {

                val myFile = it.data?.getSerializableExtra("picture") as File
                val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

                val result = rotateBitmap(
                    BitmapFactory.decodeFile(myFile.path),
                    isBackCamera
                )

                val bitmap: Bitmap = result

                val os: OutputStream = BufferedOutputStream(FileOutputStream(myFile))
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                os.close()

                getFile = myFile
                binding.previewImageView.setImageBitmap(result)
            }
        }
}
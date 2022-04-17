package com.github.HorizonParadox.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.HorizonParadox.APITest.QuestListModel
import com.github.HorizonParadox.R
import com.github.HorizonParadox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
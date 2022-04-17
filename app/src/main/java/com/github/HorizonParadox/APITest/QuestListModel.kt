package com.github.HorizonParadox.APITest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.HorizonParadox.CharacterItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class QuestListModel(application: Application):AndroidViewModel(application) {

  private val compositeDisposable = CompositeDisposable()

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
  fun fetchQuestList(questAPI: QuestAPI):CharacterItem{
     var result: CharacterItem = CharacterItem(0,"0","","","")
    compositeDisposable.add(questAPI.getQuestList()
      .subscribeOn(Schedulers.io())
      .subscribe({
         result = (CharacterItem(it.id, it.imageUrl, it.characterName, it.species, it.gender))
      },{

      }))
    return result
  }
}
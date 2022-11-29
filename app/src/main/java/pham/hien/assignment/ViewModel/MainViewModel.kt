package pham.hien.assignment.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pham.hien.assignment.Model.User

class MainViewModel : ViewModel() {

    private val TAG = "YingMing"
    val mListUserLiveData = MutableLiveData<ArrayList<User>>()
}
package pham.hien.assignment.Activity.Manga

import pham.hien.assignment.Activity.Main.BaseActivity
import pham.hien.assignment.ViewModel.ReadMangaViewModel
import pham.hien.assignment.databinding.ActivityReadMangaBinding

class ReadMangaActivity :
    BaseActivity<ActivityReadMangaBinding, ReadMangaViewModel>(ActivityReadMangaBinding::inflate) {
    override fun initListener() {

    }

    override fun getViewModel(): Class<ReadMangaViewModel> {
        return ReadMangaViewModel::class.java
    }

    override fun initObserver() {

    }

    override fun initDataDefault() {

    }
}
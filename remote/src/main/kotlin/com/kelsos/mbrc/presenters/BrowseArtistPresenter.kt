package com.kelsos.mbrc.presenters

import com.google.inject.Inject
import com.kelsos.mbrc.annotations.MetaDataType
import com.kelsos.mbrc.annotations.Queue
import com.kelsos.mbrc.constants.Constants
import com.kelsos.mbrc.domain.Artist
import com.kelsos.mbrc.interactors.LibraryArtistInteractor
import com.kelsos.mbrc.interactors.QueueInteractor
import com.kelsos.mbrc.task
import com.kelsos.mbrc.ui.views.BrowseArtistView
import roboguice.util.Ln
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber

class BrowseArtistPresenter {
  private var view: BrowseArtistView? = null
  @Inject private lateinit var artistInteractor: LibraryArtistInteractor
  @Inject private lateinit var queueInteractor: QueueInteractor

  fun bind(view: BrowseArtistView) {
    this.view = view
  }

  fun load() {
    artistInteractor.execute().task().subscribe({
      view!!.clear()
      view!!.load(it)
    }, { Ln.v(it) })
  }

  fun queue(artist: Artist, @Queue.Action action: String) {
    queueInteractor.execute(MetaDataType.ARTIST,
        action,
        artist.id.toInt()).observeOn(AndroidSchedulers.mainThread()).subscribe({ success ->
      if (success!!) {
        view!!.showEnqueueSuccess()
      } else {
        view!!.showEnqueueFailure()
      }
    }) { throwable -> view!!.showEnqueueFailure() }
  }

  fun load(page: Int) {
    artistInteractor.execute(page * Constants.PAGE_SIZE).task()
        .subscribe({ view?.load(it) }, { Timber.v(it, "Failed to load") })
  }
}
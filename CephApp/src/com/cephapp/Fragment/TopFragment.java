package com.cephapp.Fragment;
import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.TopController;

public class TopFragment extends MainFragment {
	protected MainController onControllerCreated() {
		return new TopController(this);
	}

}

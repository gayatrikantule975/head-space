package com.example.headspace;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public  class HomeFragment extends Fragment  {
   ImageSlider imageSlider;
   VideoView videoView;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_home, container, false);
      imageSlider = view.findViewById(R.id.issHome);
      videoView = view.findViewById(R.id.vvHome);
      ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
      slideModelArrayList.add(new SlideModel(R.drawable.imagesliderthree, "Slide 1", ScaleTypes.CENTER_CROP));
      slideModelArrayList.add(new SlideModel(R.drawable.imageslider, "Slide 2", ScaleTypes.CENTER_CROP));
      slideModelArrayList.add(new SlideModel(R.drawable.imagesliderfour, "Slide 3", ScaleTypes.CENTER_CROP));
      imageSlider.setImageList(slideModelArrayList);
      imageSlider.setSlideAnimation(AnimationTypes.ZOOM_IN);
imageSlider.setItemClickListener(new ItemClickListener() {
   @Override
   public void onItemSelected(int position) {
      if (position == 0) {
         Toast.makeText(getActivity(), "You Click "+position, Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(getActivity(), HomeSlide1Slider1Activity.class);
         startActivity(intent);
      } else if (position == 1) {
         Toast.makeText(getActivity(), "You Click "+position, Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(getActivity(), HomeSlide2Slider1Activity.class);
         startActivity(intent);
      } else if (position == 2) {
         Toast.makeText(getActivity(), "You Click "+position, Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(getActivity(), HomeSlide3Slider1Activity.class);
         startActivity(intent);
      }
   }
   @Override
   public void doubleClick(int i) {

   }
});

      String videoPath = "android.resource://" + getActivity().getPackageName()+"/raw/headspace";
      videoView.setVideoPath(videoPath);
      videoView.start();
      MediaController mediaController = new MediaController(getActivity());
      videoView.setMediaController(mediaController);
      return view;
   }
}


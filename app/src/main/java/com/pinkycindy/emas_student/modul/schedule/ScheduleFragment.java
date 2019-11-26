package com.pinkycindy.emas_student.modul.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinkycindy.emas_student.Base.BaseFragment;
import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.modul.schedule.inDay.DayScheduleFargament;

/**
 * Created by Pinky Cindy
 */
public class ScheduleFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //viewpager
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getFragmentManager(), tabLayout.getTabCount());

        //Menambahkan adapter ke pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
              //  tab.getIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
               // tab.getIcon().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        return view;
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        //deklarasi variabel untuk menampung jumlah tab menu yang ada
        int tabCount;

        //konstruktor
        public SectionsPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            //menginisialisasi tabcount
            this.tabCount = tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DayScheduleFargament(8);
                case 1:
                    return new DayScheduleFargament(1);
                case 2:
                    return new DayScheduleFargament(2);
                case 3:
                    return new DayScheduleFargament(3);
                case 4:
                    return new DayScheduleFargament(4);
                case 5:
                    return new DayScheduleFargament(5);
                case 6:
                    return new DayScheduleFargament(6);
                case 7:
                    return new DayScheduleFargament(0);
                default:
                    return new DayScheduleFargament(8);
            }
        }

        //Overriden method getCount untuk mengambil jumlah dari tab menu
        @Override
        public int getCount() {
            return tabCount;
        }

    }
}

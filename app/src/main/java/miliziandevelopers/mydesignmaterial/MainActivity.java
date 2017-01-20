package miliziandevelopers.mydesignmaterial;

import android.animation.Animator;
import android.app.SearchManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab = null;
    private FloatingActionButton fab1 = null;
    private FloatingActionButton fab2 = null;
    private FloatingActionButton fab3 = null;
    private int selectedTAB = 100;
    private boolean isFABOpen = false;
    private boolean click = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


// Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

// Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);
                        // TODO: handle navigation
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        // Adding Floating Action Button to bottom right of main view
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        //Trying to animate that shitfinal FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setScaleX(0);
        fab.setScaleY(0);

        reappearFloatingButton();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Snackbar!" + viewPager.getCurrentItem(),
                        Snackbar.LENGTH_LONG).show();

                click = !click;

                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                            android.R.interpolator.fast_out_slow_in);

                    fab.animate()
                            .rotation(click ? 45f : 0)
                            .setInterpolator(interpolador)
                            .start();
                } else {

                    fab.animate().rotation(click ? 45f : 0);
                }
            }

        });


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Menu 1!" + viewPager.getCurrentItem(),
                        Snackbar.LENGTH_LONG).show();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Menu 2!" + viewPager.getCurrentItem(),
                        Snackbar.LENGTH_LONG).show();
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Menu 3!" + viewPager.getCurrentItem(),
                        Snackbar.LENGTH_LONG).show();
            }


        });
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ListContentFragment(), "Productos");
        adapter.addFragment(new TileContentFragment(), "Proovedores");
        adapter.addFragment(new CardContentFragment(), "Gastos");
        viewPager.setAdapter(adapter);
        getFragmentManager().popBackStack();

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    public void changeFABcolor(Fragment fr) {
        if (fr instanceof TileContentFragment) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(228, 61, 55)));
//            fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_stock));
        } else if (fr instanceof ListContentFragment) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(228, 61, 55)));
        } else if (fr instanceof CardContentFragment) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(228, 61, 55)));
        } else {

        }
    }

    public void reappearFloatingButton(Fragment fr) {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            fab.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .setInterpolator(interpolador)
                    .setDuration(500)
                    .setStartDelay(0)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(1)
                                    .scaleX(1)
                                    .setInterpolator(interpolador)
                                    .setDuration(500)
                                    .start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        } else {


            fab.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(1)
                                    .scaleX(1);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });


        }

        changeFABcolor(fr);
    }


    public void reappearFloatingButton() {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            fab.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .setInterpolator(interpolador)
                    .setDuration(500)
                    .setStartDelay(0)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(1)
                                    .scaleX(1)
                                    .setInterpolator(interpolador)
                                    .setDuration(500)
                                    .start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        } else {


            fab.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(1)
                                    .scaleX(1);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        }


    }

    private void showFABMenu() {
        isFABOpen = true;
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
        fab3.setVisibility(View.VISIBLE);

        fab1.animate().translationY(-getResources().getDimension(R.dimen.article_keylines));
        fab1.animate().translationX(-getResources().getDimension(R.dimen.article_keylines));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationX(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fab1.animate().translationY(0);
        fab1.animate().translationX(0);
        fab2.animate().translationY(0);
        fab3.animate().translationX(0);
        fab1.setVisibility(View.INVISIBLE);
        fab2.setVisibility(View.INVISIBLE);
        fab3.setVisibility(View.INVISIBLE);

    }


}
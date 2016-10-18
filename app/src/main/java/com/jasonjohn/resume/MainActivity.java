package com.jasonjohn.resume;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends ActionBarActivity implements
        MainFragment.OnFragmentInteractionListener,
        AboutMeFragment.OnFragmentInteractionListener,
        WorkExperienceFragment.OnFragmentInteractionListener,
        ProjectExperienceFragment.OnFragmentInteractionListener,
        EducationFragment.OnFragmentInteractionListener,
        SkillsFragment.OnFragmentInteractionListener {


    public static final String URL_GITHUB = "https://github.com/jaytj95";
    public static final String URL_LINKEDIN = "https://www.linkedin.com/in/jasonjohn95";
    public static final String URL_EMAIL = "jasontjohn95@gmail.com";
    public static final String PHONE = "tel:7704032529";

    private Toolbar toolbar;

    private MainFragment mainFragment;
    private AboutMeFragment aboutMeFragment;
    private WorkExperienceFragment workExperienceFragment;
    private ProjectExperienceFragment projectExperienceFragment;
    private EducationFragment educationFragment;
    private SkillsFragment skillsFragment;
    private PDFFragment pdfFragment;

    private FrameLayout fragmentHolder;
    private FragmentTransaction fragmentTransaction;
    private Drawer drawerPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentHolder = (FrameLayout) findViewById(R.id.frag_holder);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        setupNavDrawer();

        mainFragment = new MainFragment();
        aboutMeFragment = new AboutMeFragment();
        workExperienceFragment = new WorkExperienceFragment();
        projectExperienceFragment = new ProjectExperienceFragment();
        educationFragment = new EducationFragment();
        skillsFragment = new SkillsFragment();
        pdfFragment = new PDFFragment();

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentHolder.getId(), mainFragment);
        fragmentTransaction.commit();
    }

    public void toggleDrawer() {
        if(!drawerPane.isDrawerOpen()) {
            drawerPane.openDrawer();
        } else {
            drawerPane.closeDrawer();
        }
    }
    private void setupNavDrawer() {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.space2)
                .addProfiles(
                        new ProfileDrawerItem().withName("Jason John").withEmail("jasontjohn95@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.jason_profile_2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        PrimaryDrawerItem main = new PrimaryDrawerItem().withName("Home").withIdentifier(1).withIcon(GoogleMaterial.Icon.gmd_home);
        PrimaryDrawerItem aboutMe = new PrimaryDrawerItem().withName("About Me").withIdentifier(2).withIcon(GoogleMaterial.Icon.gmd_person);
        final PrimaryDrawerItem workExperience = new PrimaryDrawerItem().withName("Work Experience").withIdentifier(3).withIcon(GoogleMaterial.Icon.gmd_work);
        PrimaryDrawerItem projectExperience = new PrimaryDrawerItem().withName("Project Experience").withIdentifier(4).withIcon(CommunityMaterial.Icon.cmd_settings_box);
        PrimaryDrawerItem education = new PrimaryDrawerItem().withName("Education").withIdentifier(5).withIcon(CommunityMaterial.Icon.cmd_school);
        PrimaryDrawerItem skills = new PrimaryDrawerItem().withName("Skills").withIdentifier(6).withIcon(CommunityMaterial.Icon.cmd_lightbulb);
        PrimaryDrawerItem resumePdf = new PrimaryDrawerItem().withName("PDF Version").withIdentifier(7).withIcon(GoogleMaterial.Icon.gmd_picture_as_pdf);

        SectionDrawerItem resumeSection = new SectionDrawerItem().withName("Jason John's Resume");
        SectionDrawerItem contactSection = new SectionDrawerItem().withName("Contact Me");
        //create the drawer and remember the `Drawer` drawerPane object
        drawerPane = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        main,
                        resumeSection,
                        aboutMe,
                        workExperience,
                        projectExperience,
                        education,
                        skills,
                        resumePdf,
                        contactSection,
                        new PrimaryDrawerItem().withName("Phone").withIcon(GoogleMaterial.Icon.gmd_phone).withIdentifier(8),
                        new PrimaryDrawerItem().withName("Email").withIcon(GoogleMaterial.Icon.gmd_email).withIdentifier(9),
                        new PrimaryDrawerItem().withName("LinkedIn").withIcon(CommunityMaterial.Icon.cmd_linkedin_box).withIdentifier(10),
                        new PrimaryDrawerItem().withName("GitHub").withIcon(CommunityMaterial.Icon.cmd_github_circle).withIdentifier(11)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        int id = drawerItem.getIdentifier();
                        switch (id) {
                            case 1:
                                fragmentTransaction(mainFragment);
                                break;
                            case 2:
                                fragmentTransaction(aboutMeFragment);
                                break;
                            case 3:
                                fragmentTransaction(workExperienceFragment);
                                break;
                            case 4:
                                fragmentTransaction(projectExperienceFragment);
                                break;
                            case 5:
                                fragmentTransaction(educationFragment);
                                break;
                            case 6:
                                fragmentTransaction(skillsFragment);
                                break;
                            case 7:
                                fragmentTransaction(pdfFragment);
                                break;
                            case 8:
                                handleContactSelection(8, PHONE);
                                break;
                            case 9:
                                handleContactSelection(9, URL_EMAIL);
                                break;
                            case 10:
                                handleContactSelection(10, URL_LINKEDIN);
                                break;
                            case 11:
                                handleContactSelection(11, URL_GITHUB);
                                break;


                        }
                        return false;
                    }
                })
                .withAccountHeader(headerResult)
                .build();
    }

    private void fragmentTransaction(Fragment frag) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentHolder.getId(), frag);
        fragmentTransaction.commit();
    }

    public void handleContactSelection(int sel, String url) {
        switch(sel) {
            case 8:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse(url));
                startActivity(phoneIntent);
                break;
            case 9:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{url});
                i.putExtra(Intent.EXTRA_SUBJECT, "Hello from Material Resume!");
                i.putExtra(Intent.EXTRA_TEXT, "Say something nice...");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case 10:
            case 11: {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse(url));
                startActivity(webIntent);
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

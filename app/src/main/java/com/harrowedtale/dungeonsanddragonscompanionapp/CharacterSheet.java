package com.harrowedtale.dungeonsanddragonscompanionapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CharacterSheet extends AppCompatActivity {
    Button nameButton;

    EditText mText1;
    EditText mText2;
    EditText mText3;
    EditText mText4;
    EditText mText5;
    EditText mText6;
    TextView tText1;
    TextView tText2;
    TextView tText3;
    TextView tText4;
    TextView tText5;
    TextView tText6;

    String buffdude;
    int bufferdude;
    String fileContents = "Hello world!";
    FileOutputStream outputStream;
    String path="/data/data/com.harrowedtale.dungeonsanddragonscompanionapp/files";
    File[] Characters=new File(path).listFiles();
    String CharacterList2[]= new String[Characters.length];
    String CharacterList[]= new String[Characters.length-1];
    //File file = new File(context.getFilesDir(), filename);

    //To view created files on android studio go to view -> Tool Windows -> Device File Explorer
    //Files are created in the data/data/com.harrowedtale.dungeonsanddragonscompanionapp/files



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_sheet);



        for (int i = 0; i < Characters.length; i++) {
            if (!Characters[i].getName().equals("customItem")) {
                Log.d("Characters", "CharacterName:" + Characters[i].getName());
                CharacterList[i] = Characters[i].getName();
            }
        }
        Spinner spinner = (Spinner) findViewById(R.id.characterSelectSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CharacterList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Button BasicButton=findViewById(R.id.BasicButton);
                Button AblitiesButton=findViewById(R.id.StatButton);
                Button ProfButton=findViewById(R.id.ProfButton);

                buffdude=read_file(parent.getItemAtPosition(position).toString());
                final String Stats[]=buffdude.split("\n");



                BasicButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button SaveButton=findViewById(R.id.SaveButton);
                        Button SkillButton=findViewById(R.id.SkillButton);
                        SkillButton.setVisibility(View.INVISIBLE);
                        String CharName=Stats[0];
                        String CharClass=Stats[1];
                        String CharLevel=Stats[2];
                        String CharHP=Stats[3];
                        String CharAlignment=Stats[4];
                        String CharRace=Stats[5];
                        mText1= (EditText) findViewById(R.id.editText1);
                        mText2= (EditText) findViewById(R.id.editText2);
                        mText3= (EditText) findViewById(R.id.editText3);
                        mText4= (EditText) findViewById(R.id.editText4);
                        mText5= (EditText) findViewById(R.id.editText5);
                        mText6= (EditText) findViewById(R.id.editText6);
                        tText1= (TextView) findViewById(R.id.textView1);
                        tText2= (TextView) findViewById(R.id.textView2);
                        tText3= (TextView) findViewById(R.id.textView3);
                        tText4= (TextView) findViewById(R.id.textView4);
                        tText5= (TextView) findViewById(R.id.textView5);
                        tText6= (TextView) findViewById(R.id.textView6);

                        tText1.setText("AC: " + String.valueOf( ((Integer.valueOf(Stats[7])-10)/2)+10 ));
                        mText1.setText("");
                        mText1.setInputType(InputType.TYPE_CLASS_TEXT);
                        bufferdude=((int) Math.ceil( Integer.valueOf(CharLevel)/4.0 ))+1;

                        tText2.setText("Prof Bonus: "+ bufferdude);
                        mText2.setText("");
                        mText2.setInputType(InputType.TYPE_CLASS_TEXT);

                        tText3.setText("Level");
                        mText3.setText("");
                        mText3.setInputType(InputType.TYPE_CLASS_NUMBER);

                        tText4.setText("Health");
                        mText4.setText("");
                        mText4.setInputType(InputType.TYPE_CLASS_NUMBER);

                        tText5.setText("");
                        mText5.setText("");
                        mText6.setInputType(InputType.TYPE_CLASS_TEXT);

                        tText6.setText("");
                        mText6.setText("");
                        mText6.setInputType(InputType.TYPE_CLASS_TEXT);

                        mText1.setText(mText1.getText()+CharName);
                        mText2.setText(mText2.getText()+CharClass);
                        mText3.setText(mText3.getText()+CharLevel);
                        mText4.setText(mText4.getText()+CharHP);
                        mText5.setText(mText5.getText()+CharAlignment);
                        mText6.setText(mText6.getText()+CharRace);
                        SaveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Stats[0]=mText1.getText().toString();
                                Stats[1]=mText2.getText().toString();
                                Stats[3]=mText3.getText().toString();
                                Stats[4]=mText4.getText().toString();
                                Stats[5]=mText5.getText().toString();
                                Stats[6]=mText6.getText().toString();
                                CreateCharacter(Stats[0],Stats);
                            }
                        });


                    }
                });

            AblitiesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button SaveButton=findViewById(R.id.SaveButton);
                    Button SkillButton=findViewById(R.id.SkillButton);
                    SkillButton.setVisibility(View.INVISIBLE);
                    String CharStr=Stats[6];
                    String CharDex=Stats[7];
                    String CharCon=Stats[8];
                    String CharInt=Stats[9];
                    String CharWis=Stats[10];
                    String CharCha=Stats[11];
                    mText1= (EditText) findViewById(R.id.editText1);
                    mText2= (EditText) findViewById(R.id.editText2);
                    mText3= (EditText) findViewById(R.id.editText3);
                    mText4= (EditText) findViewById(R.id.editText4);
                    mText5= (EditText) findViewById(R.id.editText5);
                    mText6= (EditText) findViewById(R.id.editText6);
                    tText1= (TextView) findViewById(R.id.textView1);
                    tText2= (TextView) findViewById(R.id.textView2);
                    tText3= (TextView) findViewById(R.id.textView3);
                    tText4= (TextView) findViewById(R.id.textView4);
                    tText5= (TextView) findViewById(R.id.textView5);
                    tText6= (TextView) findViewById(R.id.textView6);

                    tText1.setText("Strength ("+String.valueOf((Integer.valueOf(CharStr)-10)/2)+")");
                    mText1.setText("");
                    mText1.setInputType(InputType.TYPE_CLASS_NUMBER);

                    tText2.setText("Dexterity ("+String.valueOf((Integer.valueOf(CharDex)-10)/2)+")");
                    mText2.setText("");
                    mText2.setInputType(InputType.TYPE_CLASS_NUMBER);

                    tText3.setText("Constitution ("+String.valueOf((Integer.valueOf(CharCon)-10)/2)+")");
                    mText3.setText("");
                    mText3.setInputType(InputType.TYPE_CLASS_NUMBER);

                    tText4.setText("Intelligence ("+String.valueOf((Integer.valueOf(CharInt)-10)/2)+")");
                    mText4.setText("");
                    mText4.setInputType(InputType.TYPE_CLASS_NUMBER);

                    tText5.setText("Wisdom ("+String.valueOf((Integer.valueOf(CharWis)-10)/2)+")");
                    mText5.setText("");
                    mText5.setInputType(InputType.TYPE_CLASS_NUMBER);

                    tText6.setText("Charisma ("+String.valueOf((Integer.valueOf(CharCha)-10)/2)+")");
                    mText6.setText("");
                    mText6.setInputType(InputType.TYPE_CLASS_NUMBER);

                    mText1.setText(mText1.getText()+CharStr);
                    mText2.setText(mText2.getText()+CharDex);
                    mText3.setText(mText3.getText()+CharCon);
                    mText4.setText(mText4.getText()+CharInt);
                    mText5.setText(mText5.getText()+CharWis);
                    mText6.setText(mText6.getText()+CharCha);

                    SaveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Stats[6]=mText1.getText().toString();
                            Stats[7]=mText2.getText().toString();
                            Stats[8]=mText3.getText().toString();
                            Stats[9]=mText4.getText().toString();
                            Stats[10]=mText5.getText().toString();
                            Stats[11]=mText6.getText().toString();
                            CreateCharacter(Stats[0],Stats);
                        }
                    });
                }
            });

            ProfButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button SaveButton=findViewById(R.id.SaveButton);

                    Button SkillButton=findViewById(R.id.SkillButton);
                    SkillButton.setVisibility(View.VISIBLE);

                    String CharProf1=Stats[12];
                    String CharProf2=Stats[13];
                    String CharProf3="";
                    String CharProf4="";







                    mText1= (EditText) findViewById(R.id.editText1);
                    mText2= (EditText) findViewById(R.id.editText2);
                    mText3= (EditText) findViewById(R.id.editText3);
                    mText4= (EditText) findViewById(R.id.editText4);
                    mText5= (EditText) findViewById(R.id.editText5);
                    mText6= (EditText) findViewById(R.id.editText6);
                    tText1= (TextView) findViewById(R.id.textView1);
                    tText2= (TextView) findViewById(R.id.textView2);
                    tText3= (TextView) findViewById(R.id.textView3);
                    tText4= (TextView) findViewById(R.id.textView4);
                    tText5= (TextView) findViewById(R.id.textView5);
                    tText6= (TextView) findViewById(R.id.textView6);

                    mText1.setText("");
                    tText1.setText("");
                    mText1.setInputType(InputType.TYPE_CLASS_TEXT);

                    tText2.setText("");
                    mText2.setText("");
                    mText2.setInputType(InputType.TYPE_CLASS_TEXT);

                    tText3.setText("");
                    mText3.setText("");
                    mText3.setInputType(InputType.TYPE_CLASS_TEXT);

                    tText4.setText("");
                    mText4.setText("");
                    mText4.setInputType(InputType.TYPE_CLASS_TEXT);

                    tText5.setText("");
                    mText5.setText("");

                    tText6.setText("");
                    mText6.setText("");

                    mText1.setText(mText1.getText()+CharProf1);
                    mText2.setText(mText2.getText()+CharProf2);
                    if(Stats[1].equals("Bard")) {
                        CharProf3 = Stats[14];
                        mText3.setText(mText3.getText()+CharProf3);
                    }if(Stats[1].equals("Rogue")) {
                        CharProf3 = Stats[14];
                        mText3.setText(mText3.getText()+CharProf3);
                        CharProf4 = Stats[15];
                        mText4.setText(mText4.getText()+CharProf4);
                    }



                    SaveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Stats[12]=mText1.getText().toString();
                            Stats[13]=mText2.getText().toString();
                            if(Stats[1].equals("Bard")){
                                Stats[14]=mText3.getText().toString();
                            }if(Stats[1].equals("Rogue")) {
                                Stats[14]=mText3.getText().toString();
                                Stats[15]=mText4.getText().toString();
                            }

                            CreateCharacter(Stats[0],Stats);
                        }
                    });
                    SkillButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CharacterSheet.this, SkillList.class);
                            startActivity(intent);
                        }
                    });

                }
            });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    }


    private void writeToNewFile(String Name,String data) {
        try {
            //Context.MODE_PRIVATE creates a new file every time, this is what we want to do for the character creation wizard
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(Name, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        //This is for if we want to store a character in its own unique folder

        /*
        File directory = new File(this.getFilesDir()+File.separator+"MyFolder");

        if(!directory.exists())
            directory.mkdir();

        File newFile = new File(directory, "myText.txt");

        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try  {
            FileOutputStream fOut = new FileOutputStream(newFile);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fOut);
            outputWriter.write("Test");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        */
    }

    private void writeToFile(String Name,String data) {
        try {
            //for viewing/editing existing files we are going to need to use Context.MODE_APPEND
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(Name, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String read_file(String filename) {
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }

    public String jsonify(String input){
        String jsonString;
        jsonString=input+ "\n";
        return jsonString;
    }

    public void CreateCharacter(String Name,String Stat[]){

        writeToNewFile(Name,jsonify(Stat[0]));
        writeToFile(Name,jsonify(Stat[1]));

        writeToFile(Name,jsonify(Stat[2]));
        writeToFile(Name,jsonify(Stat[3]));
        writeToFile(Name,jsonify(Stat[4]));
        writeToFile(Name,jsonify(Stat[5]));
        writeToFile(Name,jsonify(Stat[6]));
        writeToFile(Name,jsonify(Stat[7]));
        writeToFile(Name,jsonify(Stat[8]));
        writeToFile(Name,jsonify(Stat[9]));
        writeToFile(Name,jsonify(Stat[10]));
        writeToFile(Name,jsonify(Stat[11]));
        writeToFile(Name,jsonify(Stat[12]));
        writeToFile(Name,jsonify(Stat[13]));
        if(Stat[1].equals("Bard")) {
            writeToFile(Name,jsonify(Stat[14]));
        }if(Stat[1].equals("Rogue")) {
            writeToFile(Name,jsonify(Stat[14]));
            writeToFile(Name,jsonify(Stat[15]));
        }




            /*
        writeToFile(newCharacter.getName(),jsonify(newCharacter.getLevel()));
        writeToFile(newCharacter.getName(),jsonify(newCharacter.getHP()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getAlignment()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getRace()));

        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getStrength()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getDexterity()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getConstitution()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getIntelligence()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getWisdom()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getCharisma()));

        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getFirstProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getSecondProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getThirdProficiency()));
        writeToFile(newCharacter.getName() ,jsonify(newCharacter.getFourthProficiency()));
        */
    }


}


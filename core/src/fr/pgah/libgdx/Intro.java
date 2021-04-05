package fr.pgah.libgdx;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Intro extends ApplicationAdapter {

  final int NB_SPRITES = 1;    //nous déclarons une constante avec "final"
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  ArrayList<Sprite> sprites = new ArrayList<Sprite>(NB_SPRITES);
  Joueur joueur;
  Texture gameoverTexture;
  boolean gamerover;
  

  @Override
  public void create() {
    
    batch = new SpriteBatch();
    gamerover = false;
    gameoverTexture = new Texture("gameover.jpg");
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    initialisationSprites();
    initialiserJoueur();
    
  }

  private void initialiserJoueur() {
    joueur = new Joueur();
  }

  private void initialisationSprites() {
    //sprites = new Sprite[NB_SPRITES];
    
    for (int i = 0; i < NB_SPRITES; i++){
      sprites.add( new Sprite("tortue.png"));
      //sprites [i] = new Sprite("tortue.png");    // initialise un tableau pour chaque i 
      // un constructeur = la fonction qui est appelé à la crétion de l'objet. Si il y a pas de constructeur java en initialise un tout seul par defaut.
      //sprites[i].initialiser();      
    }
  }

 @Override
  public void render() {

    if (!gamerover){

    reinitialiserArrierePlan();
    maJEtatProtagoniste();
    majEtatjeux();
    dessiner();

    }
  }

  private void reinitialiserArrierePlan() {
    // Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void maJEtatProtagoniste() {
    for(Sprite sprite:sprites) {
    //for (int i = 0; i < sprites.length; i++) {
      sprite.maJEtat();
    //sprites[i].maJEtat();
    }
    joueur.maJEtat();
  }


  private void majEtatjeux() {
    if(joueur.estEnCollisionAvec(sprites)) {
       gamerover = true;
    
    }
  }


  private void dessiner() {
    batch.begin();

    if(gamerover){
      batch.draw(gameoverTexture, 100, 100);
    }
    else {
      for(Sprite sprite:sprites) {
      //for (int i = 0; i < sprites.length; i++) {
      //sprites[i].dessiner(batch); // copîe la référence à l'objet batch 
      sprite.dessiner(batch);
    }

    joueur.dessiner(batch);
    
    }
  batch.end();
  }
}

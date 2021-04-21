package fr.pgah.libgdx;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  final int NB_SPRITES = 3; // nous déclarons une constante avec "final"
  final int NB_BONUS = 1;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  ArrayList<Sprite> sprites = new ArrayList<Sprite>(NB_SPRITES);
  ArrayList<bonus> bonuss = new ArrayList<bonus>(NB_BONUS);
  Joueur joueur;
  Texture gameoverTexture;
  boolean gameOver;
  Souris souris;
  bonus indexBonus;
  ArrayList<Sprite> spriteTouches = new ArrayList<>();
  Scanner lancerjeu = new Scanner(System.in);

  @Override
  public void create() {

    batch = new SpriteBatch();
    gameOver = false;
    gameoverTexture = new Texture("gameover.jpg");
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    initialisationSprites();
    initialiserJoueur();
    initialiserSouris();
    initialisationBonus();
    lancergame();

  }

  private void lancergame() {

    System.out.println("voulez vous lancer le jeu yes/no");
    String a = lancerjeu.nextLine();
    if (a.equals("yes")) {
      gameOver = false;
    } else {
      gameOver = true;
    }
  }

  private void initialisationBonus() {
    for (int i = 0; i < NB_BONUS; i++) {
      bonuss.add(new bonus("black.png"));
    }
  }

  private void initialiserJoueur() {
    joueur = new Joueur();
  }

  private void initialiserSouris() {
    souris = new Souris();
  }

  private void initialisationSprites() {
    // sprites = new Sprite[NB_SPRITES];

    for (int i = 0; i < NB_SPRITES; i++) {
      sprites.add(new Sprite("tortue.png")); // avec add pour fournir au sprite notre image
      // sprites [i] = new Sprite("tortue.png"); // initialise un tableau pour chaque
      // i
      // un constructeur = la fonction qui est appelé à la crétion de l'objet. Si il y
      // a pas de constructeur java en initialise un tout seul par defaut.
      // sprites[i].initialiser();
    }
  }

  @Override
  public void render() {

    if (!gameOver) {

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
    for (Sprite sprite : sprites) {
      // for (int i = 0; i < sprites.length; i++) {
      sprite.maJEtat();
      // sprites[i].maJEtat();
    }

    for (bonus bonus : bonuss) {
      bonus.majEtat();
    }

    joueur.maJEtat();
    souris.maJEtat();
  }

  private void majEtatjeux() {
    if (joueur.estEnCollisionAvec(sprites)) {
      gameOver = true;
    }
    if (souris.clicGauchetir()) {

    }
    for (Sprite sprite : sprites) {
      if (souris.clicGauche() && sprite.seTrouveSur(souris)) {
        // spriteTouches = sprites; // retenir le sprite dans une variable pour supprimé
        // le sprite en dehors du for
        spriteTouches.add(sprite); // retenir le sprite dans une variable pour
        // supprimé le sprite en dehors du for
      }
    }
    for (bonus bonus : bonuss) {
      if (souris.clicGauche() && bonus.CollisionAvec(souris)) {
        indexBonus = bonus;
        souris.NBshoot += 3;
        System.out.println("il vous reste " + souris.NBshoot + " balles");
      }
    }
    sprites.removeAll(spriteTouches); // supprimer tous les sprites qui se trouve sur le clique
    bonuss.remove(indexBonus);
    // ne faut surtout pas supprimé un élément dans un for

    if (sprites.isEmpty()) { // Quand il n'y a plus de sprite alors affiché une image ici gameover
      gameOver = true;
    }

    if (souris.NBshoot == 0) {
      gameOver = true;
      System.out.println("Vous n'avez plus de balles");
    }
  }

  private void dessiner() {
    batch.begin();

    if (gameOver) {
      batch.draw(gameoverTexture, 50, 50);
    } else {
      for (Sprite sprite : sprites) {
        // for (int i = 0; i < sprites.length; i++) {
        // sprites[i].dessiner(batch); // copîe la référence à l'objet batch
        sprite.dessiner(batch);
      }
      for (bonus bonus : bonuss) {
        bonus.dessiner(batch);
      }
      joueur.dessiner(batch);
      souris.dessiner(batch);

    }
    batch.end();
  }
}

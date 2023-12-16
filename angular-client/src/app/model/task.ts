export class Task {

  protected static ignore = ['id', 'ownerId'];

  id: null | string = null;

  title: null | string = null;

  description: null | string = null;

  ownerId: null | number = null;

  toJson(): { [name: string]: any } {
    let json = {};

    Object.getOwnPropertyNames(this).filter(name => Task.ignore.indexOf(name) < 0).forEach(name => {
      // @ts-ignore
      json[name] = this[name];
    });

    return json;
  }
}

import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type FeatureItem = {
  title: string;
  description: JSX.Element;
};

const FeatureList: FeatureItem[] = [
  {
    title: 'Based on Espresso',
    description: (
      <>
        Framework for writing Android Native UI test. Provided and maintained by Google.
      </>
    ),
  },
  {
    title: 'Nice and simple DSL',
    description: (
      <>
        Kakao Compose wrapping Espresso to provide nice and simple DSL to improve readability
          and reusability with Page Object pattern
      </>
    ),
  },
  {
    title: 'Many custom Assertions',
    description: (
      <>
        Kakao can automatically setup all required semantics and provide type-based view for
          easiest assertions.
      </>
    ),
  },
];

function Feature({title, description}: FeatureItem) {
  return (
    <div className={clsx('col col--4')}>
        <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures(): JSX.Element {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
